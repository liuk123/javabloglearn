package com.lk.fishblog.controller;

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
    public String addArticle(@RequestBody NewArticleRequest a){
        User author = userService.findById(a.getAuthorId());
        log.info("Coffee {}:", author);
        return articleService.saveA(a.getTitle(),a.getContent(), author);
    }

    @GetMapping(path="/{id}")
    public Article getArticle(@PathVariable Long id){

        Article a = articleService.findById(id);
        log.info("Coffee {}:", a);
        return a;
    }
    @GetMapping(path="/articlesByAuthor/{id}")
    public Page<Article> getArticlesByAuthor(@PathVariable Long id){
        Page<Article> a = articleService.findByAuthor(id,0,10);
        log.info("Coffee {}:", a);
        return a;
    }
    @DeleteMapping(path = "del/{id}")
    public Void delArticle(@PathVariable Long id){
        articleService.deleteById(id);
        return  null;
    }
}
