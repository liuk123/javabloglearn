package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewArticleRequest;
import com.lk.fishblog.model.Article;
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

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addArticle(@RequestBody NewArticleRequest a){
        User author = userService.findById(a.getAuthorId());
        log.info("Coffee {}:", author);
        articleService.save(a.getTitle(),a.getContent(), author);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功");
    }

    @GetMapping(path="/{id}")
    public ResultSet getArticleById(@PathVariable Long id){
        Article a = articleService.findById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", a);
    }

    @GetMapping(path="/articlesByAuthor/{id}")
    public Page<Article> getArticlesByAuthor(@PathVariable Long id){
        Page<Article> a = articleService.findByAuthor(id,0,10);
        log.info("Coffee {}:", a);
        return a;
    }

    @DeleteMapping(path = "del/{id}")
    public ResultSet delArticle(@PathVariable Long id){
        articleService.deleteById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
