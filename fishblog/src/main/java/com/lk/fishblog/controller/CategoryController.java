package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewCategoryRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Category;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 获取分类
     * @return
     */
    @GetMapping(path="/")
    public ResultSet getByAuthor(@RequestParam Long id){
        List<Category> t = categoryService.findByAuthor(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", t);
    }

    /**
     * 添加分类
     * @param categoryRequest
     * @param authentication
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewCategoryRequest categoryRequest, Authentication authentication){
        User u = (User) authentication.getPrincipal();
        Article a = new Article(categoryRequest.getArticleId());
        List<Article> aList = new ArrayList<>();
        aList.add(a);
        Category category = categoryService.save(categoryRequest.getId(), categoryRequest.getName(), u, aList);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功", category);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        categoryService.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

}
