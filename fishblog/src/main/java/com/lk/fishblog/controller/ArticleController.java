package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.*;
import com.lk.fishblog.controller.request.NewArticleRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Tag;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import com.lk.fishblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    RegUtil regUtil;
    @Autowired
    FileUtil fileUtil;

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.temPath}")
    private String uploadTemPath;

    /**
     * 添加文章
     * @param a 文章
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid  NewArticleRequest a, Authentication authentication){
        User user = (User) authentication.getPrincipal();
//图片从缓存文件夹移入正式文件夹
        List<String> urlList = regUtil.extractUrls(a.getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String format = sdf.format(new Date());
        File newFile = new File(uploadPath + format);
        for(String url: urlList){
            System.out.println("url:"+url);
            File oldFile = new File(url.substring(url.indexOf(uploadTemPath),url.lastIndexOf(')')));
            fileUtil.moveFile(oldFile.getAbsolutePath(), newFile.getAbsolutePath());
        }
//文章中图片地址替换
        String str = "(!\\[.*?]\\(.+?)"+uploadTemPath+"(.+?\\))";
        a.setContent(a.getContent().replaceAll(str, "$1"+uploadPath+"$2"));
//修改文章时，删除多余图片
        if(null != a.getId()){
            Article oldA = articleService.findById(a.getId());
            if(null != oldA){
                List<String> urlOldList = regUtil.extractUrls(oldA.getContent());
                for(String oldUrl: urlOldList){
                    if(!urlList.contains(oldUrl)){
                        File oldFile = new File(oldUrl.substring(oldUrl.indexOf(uploadPath),oldUrl.lastIndexOf(')')));
                        fileUtil.delFile(oldFile.getAbsolutePath());
                    }
                }
            }
        }

        List<Tag> tagList = new ArrayList<>();
        for(Long val: a.getTagList()){
            tagList.add(new Tag(val));
        }

        Article article = articleService.save(a.getId(),a.getTitle(),a.getContent(), a.getDescItem(), tagList, new User(user.getId(),user.getUsername()));
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", article.getId());
    }

    /**
     * 文章详情
     * @param id 文章id
     */
    @GetMapping(path="/{id}")
    public ResultSet getById(@PathVariable Long id){
        Article a = articleService.findById(id);
        List<Tag> tagList = new ArrayList<>(a.getTagList());
        Article article = new Article();
        article.setId(a.getId());
        article.setTitle(a.getTitle());
        article.setUpdateTime(a.getUpdateTime());
        article.setAuthor(a.getAuthor());
        article.setTagList(tagList);
        article.setCommentList(a.getCommentList());
        article.setCreateTime(a.getCreateTime());
        article.setContent(a.getContent());
        article.setDescItem(a.getDescItem());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", article);
    }

    /**
     * 列表
     * @param pageIndex 页码
     * @param pageSize 页数
     * @param tags 类别
     */
    @GetMapping(path="/")
    public PageInfo<Article> getAll(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam List<Long> tags){

        Page<Article> a;
        if(tags.isEmpty()){
            a = articleService.findAll(pageIndex-1, pageSize);
        }else{
            List<Tag> tagList = new ArrayList<>();
            for(Long val: tags){
                tagList.add(new Tag(val));
            }
            a = articleService.findByTaglist(pageIndex-1, pageSize, tagList);
        }
        for(Article article: a.getContent()){
            List<Tag> taglist = new ArrayList<>(article.getTagList());
            article.setTagList(taglist);
        }
        PageInfo<Article> page = new PageInfo(a);
        page.setPageSize(pageSize);
        return page;
    }

    /**
     * 文章列表
     * @param id 用户id
     * @param pageIndex 页码
     * @param pageSize 页数
     */
    @GetMapping(path="/getByAuthor/{id}")
    public PageInfo<Article> getByAuthor(@PathVariable Long id, @RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Page<Article> a = articleService.findByAuthor(id, pageIndex-1, pageSize);
        for(Article article: a.getContent()){
            List<Tag> tags = new ArrayList<>(article.getTagList());
            article.setTagList(tags);
        }
        PageInfo<Article> page = new PageInfo(a);
        return page;
    }

    /**
     * 删除文章
     * @param id 文章id
     */
    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        Article a = articleService.findById(id);
        List<String> urlList = regUtil.extractUrls(a.getContent());
        for(String url: urlList){
            File oldFile = new File(url.substring(url.indexOf(uploadPath),url.lastIndexOf(')')));
            fileUtil.delFile(oldFile.getAbsolutePath());
        }

        articleService.deleteById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
