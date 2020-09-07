package com.lk.fishblog.controller;

import com.lk.fishblog.controller.request.NewCommentRequest;
import com.lk.fishblog.controller.request.NewReplyRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.Reply;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import com.lk.fishblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Slf4j
public class ReplyController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @GetMapping(path="/{id}")
    public Reply getById(@PathVariable Long id){
        Reply a = replyService.findById(id);
        log.info("Reply {}:", a);
        return a;
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Reply add(@RequestBody NewReplyRequest r){
        User fu = userService.findById(r.getFromUserId());
        User tu = userService.findById(r.getToUserId());
        log.info("getByIdFromUser {}:", fu);
        log.info("getByIdToUser {}:", tu);
        Comment c = commentService.findById(r.getCommentId());
        log.info("Comment {}:", c);
        return replyService.save(c.getContent(), c, fu, tu);
    }

    @DeleteMapping(path = "del/{id}")
    public String del(@PathVariable Long id){
        replyService.deleteById(id);
        return "success";
    }

}
