package com.lk.fishblog.controller;

import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class CommentController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;

}
