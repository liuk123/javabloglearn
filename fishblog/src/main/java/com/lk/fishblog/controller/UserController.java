package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewUserRequest;
import com.lk.fishblog.model.User;
import com.lk.fishblog.security.MyPasswordEncoder;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import com.lk.fishblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    /**
     * 获取当前登录人信息
     * @param authentication
     * @return
     */
    @GetMapping(path="/currentUser")
    public ResultSet getCurrentUserBySession(Authentication authentication){
        User u = (User) authentication.getPrincipal();
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "获取用户信息", new User(u.getId(),u.getUsername()));
    }

    /**
     * 注册
     * @param u
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewUserRequest u){
        MyPasswordEncoder encoder = new MyPasswordEncoder();
        return userService.register(u.toUser(encoder));
    }

}
