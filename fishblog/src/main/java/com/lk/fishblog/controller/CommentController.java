package com.lk.fishblog.controller;

import com.lk.fishblog.controller.request.NewArticleRequest;
import com.lk.fishblog.controller.request.NewCommentRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import com.lk.fishblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @GetMapping(path="/{id}")
    public Comment getById(@PathVariable Long id){
        Comment a = commentService.findById(id);
        log.info("Coffee {}:", a);
        return a;
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment add(@RequestBody NewCommentRequest c){
        User u = userService.findById(c.getFromUserId());
        log.info("CommentUser {}:", u);
        Article a = articleService.findById(c.getArticleId());
        log.info("CommentArticle {}:", a);
        return commentService.save(c.getContent(), u, a);
    }

    @DeleteMapping(path = "del/{id}")
    public String del(@PathVariable Long id){
        commentService.deleteById(id);
        return "success";
    }

}
