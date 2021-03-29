package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.CookieUtil;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewReplyRequest;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/reply")
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
    @Autowired
    CookieUtil cookieUtil;

    @GetMapping(path="/{id}")
    public ResultSet getById(@PathVariable Long id){
        Reply a = replyService.findById(id);
        log.info("Reply {}:", a);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", a);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(HttpServletRequest request, @RequestBody @Valid NewReplyRequest r){
        User fu =cookieUtil.getLoginUser(request);
        if(fu == null){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE,"请重新登录");
        }else if(fu.getRole()<10){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE,"没有权限");
        }
        User tu = new User(r.getToUserId(), r.getToUsername());
        Comment c = new Comment(r.getCommentId());
        Reply reply = replyService.save(r.getContent(), c, fu, tu);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功", reply);
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        replyService.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

}
