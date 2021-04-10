package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.MyUserDetails;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

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
    public ResultSet getById(@PathVariable Long id){
        User u = userService.findById(id);
        log.info("Coffee {}:", u);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", u);
    }

    /**
     * 获取当前登录人信息
     * @param authentication
     * @return
     */
    @GetMapping(path="/currentUser")
    public ResultSet getCurrentUserBySession(Authentication authentication){
//        return userService.getCurrentUserBySession(request);
        MyUserDetails u = (MyUserDetails) authentication.getPrincipal();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "获取用户信息", new User(u.getId(),u.getUsername()));
    }

    /**
     * 注册
     * @param u
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(HttpServletResponse response, @RequestBody @Valid NewUserRequest u){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(u.getPassword()));
        return userService.register(response, u.getUsername(),encoder.encode(u.getPassword()),u.getPhone(),10);
    }

    /**
     * 登录
     * @param response
     * @param
     * @return
     */
    @GetMapping(path="/login")
    public ResultSet login(HttpServletResponse response,@RequestParam String password, @RequestParam String phone){
        return userService.login(response,phone,password);
    }
    /**
     * 退出
     * @param req
     * @param
     * @return
     */
    @GetMapping(path="/logout")
    public ResultSet logout(HttpServletRequest req){
        return userService.logout(req);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id, HttpServletRequest req){
        userService.deleteById(id, req);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
