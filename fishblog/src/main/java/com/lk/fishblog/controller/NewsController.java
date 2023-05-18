package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewNewsRequest;
import com.lk.fishblog.model.News;
import com.lk.fishblog.model.Rss;
import com.lk.fishblog.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {
    @Autowired
    NewsService newsService;

    /**
     * 获取书签分类
     * @param
     * @return
     */
    @GetMapping(path={"/{id}", "/"})
    public ResultSet getLink(@PathVariable(required = false) Long id){
        List<News> n = newsService.findAll();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveLink(@RequestBody @Valid List<NewNewsRequest> newNewsListRequest){
        List<News> newsList = new ArrayList();
        for(NewNewsRequest newNewsRequest: newNewsListRequest){
            News news = News.builder()
                    .id(newNewsRequest.getId())
                    .title(newNewsRequest.getTitle())
                    .link(newNewsRequest.getLink())
                    .descItem(newNewsRequest.getDescItem())
                    .rss(new Rss(newNewsRequest.getRssId()))
                    .build();
            newsList.add(news);
        }
        newsService.deleteAll();
        newsService.save(newsList);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",null);
    }
}
