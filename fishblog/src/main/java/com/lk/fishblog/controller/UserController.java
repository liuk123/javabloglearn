package com.lk.fishblog.controller;

import com.lk.fishblog.controller.request.NewUserRequest;
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
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @GetMapping(path="/{id}")
    public User getById(@PathVariable Long id){
        User u = userService.findById(id);
        log.info("Coffee {}:", u);
        return u;
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User add(@RequestBody NewUserRequest c){
        return userService.save(c.getUsername(), c.getPassword(), 10);
    }

    @DeleteMapping(path = "del/{id}")
    public String del(@PathVariable Long id){
        userService.deleteById(id);
        return "success";
    }

}
