package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.*;
import com.lk.fishblog.controller.request.NewArticleRequest;
import com.lk.fishblog.controller.request.NewCollectRequest;
import com.lk.fishblog.model.*;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import com.lk.fishblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/article")
@Slf4j
@CacheConfig(cacheNames = "ArticleCache")
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
        if(authentication==null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE,"请登录");
        }
        User user = (User) authentication.getPrincipal();
//图片从缓存文件夹移入正式文件夹
        List<String> urlList = regUtil.extractUrls(a.getContent());
        if(a.getPostImage() != null){
            String postImageStr = a.getPostImage().substring(0, a.getPostImage().indexOf('?'));
            if(!urlList.contains(postImageStr)){
                urlList.add(postImageStr);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String format = sdf.format(new Date());
        File newFile = new File(uploadPath + format);
        for(String url: urlList){
            int index = url.indexOf(uploadTemPath);
            if(index != -1){
                File oldFile = new File(url.substring(index));
                fileUtil.moveFile(oldFile.getAbsolutePath(), newFile.getAbsolutePath());
            }
        }
//文章中图片地址替换
        String str = "(!\\[.*?]\\(.+?)"+uploadTemPath+"(.+?\\))";
        a.setContent(a.getContent().replaceAll(str, "$1"+uploadPath+"$2"));
        if(a.getPostImage()!= null) {
            a.setPostImage(a.getPostImage().replace(uploadTemPath, uploadPath));
        }
//修改文章时，删除多余图片
        if(null != a.getId()){
            Article oldA = articleService.findById(a.getId());
            if(null != oldA){
                List<String> urlOldList = regUtil.extractUrls(oldA.getContent());
                if(oldA.getPostImage()!= null && !urlOldList.contains(oldA.getPostImage())){
                    urlOldList.add(oldA.getPostImage());
                }
                for(String oldUrl: urlOldList){
                    if(oldUrl!=null && !urlList.contains(oldUrl)){
                        int index = oldUrl.indexOf(uploadPath);
                        if(index != -1){
                            File oldFile = new File(oldUrl.substring(index));
                            fileUtil.delFile(oldFile.getAbsolutePath());
                        }
                    }
                }
            }
        }

        Category c = null;
        if(a.getCategory() != null){
            c = new Category(a.getCategory());
        }else{
            c = new Category();
        };
        Article article = articleService.save(
                a.getId(),
                a.getTitle(),
                a.getContent(),
                a.getDescItem(),
                new Tag(a.getTagId()),
                c,
                new User(user.getId()),
                a.getPostImage(),
                new TagColumn(a.getTagColumnId()),
                a.getKeyword(),
                a.getType());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", article.getId());
    }

    /**
     * 文章详情
     * @param id 文章id
     */
    @GetMapping(path="/{id}")
    public ResultSet getById(Authentication authentication, @PathVariable Long id){

        Article a = articleService.findById(id);
        if(a==null){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "无法查找到", null);
        }else if(a.getType()<=0){
            if(authentication!=null){
                User u = (User) authentication.getPrincipal();
                if(!u.getId().equals(a.getAuthor().getId())){
                    return new ResultSet(ResultSet.RESULT_CODE_FALSE, "无权查看", null);
                }
            }else{
                return new ResultSet(ResultSet.RESULT_CODE_FALSE, "无权查看", null);
            }

        }
        Article article = new Article();

        User au = a.getAuthor();
        User u = new User(au.getId(),au.getUsername(), au.getAvatar());
        article.setId(a.getId());
        article.setTitle(a.getTitle());
        article.setUpdateTime(a.getUpdateTime());
        article.setAuthor(u);
        article.setTag(a.getTag());

        article.setCreateTime(a.getCreateTime());
        article.setContent(a.getContent());
        article.setDescItem(a.getDescItem());
        article.setTagColumn(a.getTagColumn());
        article.setCategory(a.getCategory());
        article.setKeyword(a.getKeyword());
        article.setType(a.getType());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", article);
    }

    /**
     * 首页获取文章列表
     * @param pageIndex 页码
     * @param pageSize 页数
     * @param tagIds 类别
     * @param tagColumnId 栏目
     */
    @GetMapping(path="/")
    public PageInfo<Article> getArticle(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = false) List<Long> tagIds, @RequestParam(required = false) Long tagColumnId){

        List<Long> types = new ArrayList<>(Arrays.asList(1L, 2L));
        Page<Article> a = null;
        if (tagIds!=null&&!tagIds.isEmpty()){
            a = articleService.findByTagList(pageIndex-1, pageSize, tagIds, types);
        }else if(tagColumnId!=null){
            a = articleService.findByTagColumn(pageIndex-1, pageSize, tagColumnId, types);
        }else{
            a = articleService.findAllByPage(pageIndex-1, pageSize, types);
        }
        if(a == null){
            return null;
        }
        return getArticlePageInfo(a);
    }

    /**
     * 根据用户获取文章列表
     * @param id 用户id
     * @param pageIndex 页码
     * @param pageSize 页数
     */
    @GetMapping(path="/getByAuthor/")
    public PageInfo<Article> getByAuthor(Authentication authentication, @RequestParam Long id, @RequestParam(required = false) Long categoryId, @RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        List<Long> types = new ArrayList<>(Arrays.asList(1L, 2L));
        if(authentication != null){
            User u = (User) authentication.getPrincipal();
            if(u.getId().equals(id)){
                types.add(0L);
            }
        }
        Page<Article> a;
        if(categoryId==null){
            a = articleService.findByAuthor(id, pageIndex-1, pageSize, types);
        }else{
            a = articleService.findByAuthorAndCategory(id, categoryId, pageIndex-1, pageSize, types);
        }
        return getArticlePageInfo(a);
    }

    private PageInfo<Article> getArticlePageInfo(Page<Article> a) {
        List<Article> lista = new ArrayList<>();
        for(Article article: a.getContent()){
            Article reta = new Article();
            User au = article.getAuthor();
            User u = new User(au.getId(),au.getUsername(),au.getAvatar());
            reta.setAuthor(u);
            reta.setId(article.getId());
            reta.setCategory(article.getCategory());
            reta.setCreateTime(article.getCreateTime());
            reta.setUpdateTime(article.getUpdateTime());
            reta.setDescItem(article.getDescItem());
            reta.setPostImage(article.getPostImage());
            reta.setTitle(article.getTitle());
            reta.setTag(article.getTag());
            reta.setKeyword(article.getKeyword());
            reta.setType(article.getType());
            lista.add(reta);
        }
        PageInfo<Article> page = new PageInfo<Article>(a.getNumber()+1, a.getSize(), a.getTotalPages(),a.getTotalElements(),lista);
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
        if(a.getPostImage() != null){
            String postImageStr = a.getPostImage().substring(0, a.getPostImage().indexOf('?'));
            if(!urlList.contains(postImageStr)){
                urlList.add(postImageStr);
            }
        }
        for(String url: urlList){
            File oldFile = new File(url.substring(url.indexOf(uploadPath)));
            fileUtil.delFile(oldFile.getAbsolutePath());
        }

        articleService.deleteById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

    /**
     * 获取收藏
     * @param authentication
     * @return
     */
    @GetMapping(path="/collect/")
    public PageInfo<Collect> collect(Authentication authentication, @RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        if(authentication==null){
            return new PageInfo<Collect>(ResultSet.RESULT_CODE_TRUE, "请登录");
        }
        User u = (User) authentication.getPrincipal();
        Page<Collect> collect = articleService.findCollectList(u.getId(), pageIndex-1, pageSize);
        PageInfo<Collect> page = new PageInfo(collect);
        return  page;
    }
    /**
     * 判断是否收藏
     * @param authentication
     * @return
     */
    @GetMapping(path="/collect/is")
    public ResultSet collectById(Authentication authentication, @RequestParam Long articleId){
        if(authentication==null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE,"请登录");
        }
        User u = (User) authentication.getPrincipal();
        Boolean c = articleService.existsCollectById(u.getId(), articleId);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "收藏", c);
    }
    /**
     * 保存收藏
     * @param collectRequest
     * @return
     */
    @PostMapping(path = "/collect/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addCollect(@RequestBody @Valid NewCollectRequest collectRequest, Authentication authentication){
        if(authentication==null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE,"请登录");
        }
        User u = (User) authentication.getPrincipal();
        Article a = new Article(collectRequest.getArticleId());
        articleService.saveCollect(u, a);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "收藏成功");
    }

    /**
     * 删除收藏
     * @param articleId 文章id
     */
    @DeleteMapping(path = "/collect/{articleId}")
    public ResultSet delCollect(@PathVariable Long articleId, Authentication authentication){
        if(authentication==null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE,"请登录");
        }
        User u = (User) authentication.getPrincipal();
        Article a = new Article(articleId);
        Collect c = new Collect(u, a);
        articleService.deleteCollect(c);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
