package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.CookieUtil;
import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
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
    CookieUtil cookieUtil;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(HttpServletRequest request, @RequestBody @Valid  NewArticleRequest a){
        User user =cookieUtil.getLoginUser(request);
        if(user == null){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE,"请重新登录");
        }
        List<Tag> tagList = new ArrayList<>();
        for(Long val: a.getTagList()){
            tagList.add(new Tag(val));
        }
        Article article = articleService.save(a.getTitle(),a.getContent(), a.getDesc(), tagList, user);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", article.getId());
    }

    @GetMapping(path="/{id}")
    public ResultSet getById(@PathVariable Long id){
        Article a = articleService.findById(id);
        log.info("id {}:",a.getCommentList());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", a);
    }

    @GetMapping(path="/")
    public PageInfo<Article> getAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam List<Long> tags){

        Page<Article> a;
        if(tags.isEmpty()){
            a = articleService.findAll(pageNum-1, pageSize);
        }else{
            List<Tag> tagList = new ArrayList<>();
            for(Long val: tags){
                tagList.add(new Tag(val));
            }
            a = articleService.findByTaglist(pageNum-1, pageSize, tagList);
        }
        PageInfo<Article> page = new PageInfo(a);
        page.setPageSize(pageSize);
        return page;
    }

    @GetMapping(path="/getByAuthor/{id}")
    public PageInfo<Article> getByAuthor(@PathVariable Long id, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        Page<Article> a = articleService.findByAuthor(id, pageNum, pageSize);
        PageInfo<Article> page = new PageInfo(a);
        return page;
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        articleService.deleteById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
