package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewUserRequest;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import com.lk.fishblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
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
    public ResultSet getById(@PathVariable Long id){
        User u = userService.findById(id);
        log.info("Coffee {}:", u);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", u);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewUserRequest u){
//        User us = userService.findByUsernameOrPhone(u.getUsername(), u.getPhone());
//        log.info("ex: ",us);
        User user = userService.save(u.getUsername(), u.getPassword(),u.getPhone(), 10);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功", user.getId());
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

    @GetMapping(path="login")
    public ResultSet login(HttpServletResponse response,@RequestBody @Valid NewUserRequest u){
        return userService.login(response,u.getUsername(),u.getPassword());
    }
}
