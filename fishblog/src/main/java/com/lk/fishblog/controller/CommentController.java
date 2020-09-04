package com.lk.fishblog.controller;

import com.lk.fishblog.model.Reply;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;

}
