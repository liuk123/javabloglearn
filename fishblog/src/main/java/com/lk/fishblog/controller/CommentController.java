package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewCommentRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.Reply;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    CommentService commentService;

//    @GetMapping(path="/{id}")
//    public ResultSet getById(@PathVariable Long id){
//        Comment c = commentService.findById(id);
//        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", c);
//    }
    @GetMapping(path="/")
    public PageInfo<Comment> getComment(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = false) Long id){
        Page<Comment> c = this.commentService.findByArticleId(id, pageIndex-1, pageSize);
        for(Comment comment: c.getContent()){
            comment.setReplyList(comment.getReplyList().stream().limit(100).collect(Collectors.toList()));
        }

        PageInfo<Comment> page = new PageInfo<>(c);
        return page;
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
        Comment comment = commentService.save(c.getContent(), new User(user.getId(), user.getUsername(), user.getAvatar()), a);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功", comment);
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        commentService.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
