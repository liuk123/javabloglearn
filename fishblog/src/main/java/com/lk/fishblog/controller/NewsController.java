package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewNewsCategoryRequest;
import com.lk.fishblog.controller.request.NewNewsRequest;
import com.lk.fishblog.model.News;
import com.lk.fishblog.model.NewsCategory;
import com.lk.fishblog.service.NewsCategoryService;
import com.lk.fishblog.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    NewsCategoryService newsCategoryService;

    /**
     * 获取书签分类
     * @param
     * @return
     */
    @GetMapping(path={"/{id}", "/"})
    public ResultSet getLink(@PathVariable(required = false) Long id){
        List<News> n;
        if(id == null){
            n = newsService.findAll();
        }else{
            n = newsService.findNews(id);
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveLink(@RequestBody @Valid List<NewNewsRequest> newNewsListRequest){
        List<News> newsList = new ArrayList();
        for(NewNewsRequest newNewsRequest: newNewsListRequest){
            News news = News.builder()
                    .id(newNewsRequest.getId())
                    .sort(newNewsRequest.getSort())
                    .title(newNewsRequest.getTitle())
                    .link(newNewsRequest.getLink())
                    .newsCategory(new NewsCategory(newNewsRequest.getCategoryId()))
                    .descItem(newNewsRequest.getDescItem())
                    .type(newNewsRequest.getType())
                    .build();
            newsList.add(news);
        }
        newsService.save(newsList);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",null);
    }
    @PostMapping(path = "/newsCategory/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNewsCategory(@RequestBody @Valid NewNewsCategoryRequest newNewsCategoryRequests){
        NewsCategory nc =newsCategoryService.save(newNewsCategoryRequests.getId(),newNewsCategoryRequests.getSort(), newNewsCategoryRequests.getTitle());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",nc);
    }

    @GetMapping(path={"/newCategory/{id}","/newCategory/"})
    public ResultSet getNewCategory(@PathVariable(required = false) Long id){
        List<NewsCategory> nc;
        Optional<NewsCategory> n;
        if(id == null){
            nc = newsCategoryService.findAll();
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", nc);
        }else{
            n = newsCategoryService.findById(id);
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
        }
    }
}
