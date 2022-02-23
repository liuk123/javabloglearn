package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.CookieUtil;
import com.lk.fishblog.common.utils.ResultSet;
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
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @Autowired
    CookieUtil cookieUtil;

    @GetMapping(path="/{id}")
    public ResultSet getById(@PathVariable Long id){
        Comment c = commentService.findById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", c);
    }

    @GetMapping(path="top5/{id}")
    public ResultSet getTop5ByArticleId(@PathVariable Long id){
        List<Comment> c = commentService.findTop5ByArticleId(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", c);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewCommentRequest c, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Article a = new Article(c.getArticleId());
        Comment comment = commentService.save(c.getContent(), new User(user.getId()), a);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功", comment);
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        commentService.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
