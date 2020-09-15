package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewArticleRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Tag;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.CommentService;
import com.lk.fishblog.service.ReplyService;
import com.lk.fishblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/article")
@Slf4j
public class ArticleController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid  NewArticleRequest a){
        User author = new User(1L);
        List<Tag> tagList = new ArrayList<>();
        for(Long val: a.getTagList()){
            tagList.add(new Tag(val));
        }
        Article article = articleService.save(a.getTitle(),a.getContent(), tagList, author);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", article.getId());
    }

    @GetMapping(path="/{id}")
    public ResultSet getById(@PathVariable Long id){
        Article a = articleService.findById(id);
        log.info("id {}:",a.getCommentList());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", a);
    }

    @GetMapping(path="/getByAuthor/{id}")
    public Page<Article> getByAuthor(@PathVariable Long id){
        Page<Article> a = articleService.findByAuthor(id,0,10);
        log.info("Coffee {}:", a);
        return a;
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        articleService.deleteById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
