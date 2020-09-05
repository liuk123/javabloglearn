package com.lk.fishblog.controller;

import com.lk.fishblog.model.Comment;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;

    @GetMapping(path="/{id}")
    public Comment getById(@PathVariable Long id){

        Comment a = commentService.findById(id);
        log.info("Coffee {}:", a);
        return a;
    }

}
