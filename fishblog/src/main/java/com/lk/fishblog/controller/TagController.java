package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewTagRequest;
import com.lk.fishblog.model.Tag;
import com.lk.fishblog.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    ArticleService articleService;

    @GetMapping(path="/")
    public ResultSet getAllById(){
        List<Tag> t = tagService.findAll();
        log.info("Coffee {}:", t);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", t);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewTagRequest t){
        Tag tag = tagService.save(t.getName());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功", tag);
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        tagService.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

}
