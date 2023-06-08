package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewLinkRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Rss;
import com.lk.fishblog.service.RssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rss")
@Slf4j
@CacheConfig(cacheNames = "RssCache")
public class RssController {
    @Autowired
    RssService rssService;
    /**
     * 获取书签分类
     * @param
     * @return
     */
    @GetMapping(path="/")
    public PageInfo<Rss> getLinkAll(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Page<Rss> n = rssService.find(pageIndex-1, pageSize);
        PageInfo<Rss> page = new PageInfo<>(n);
        return page;
    }
    @GetMapping(path="/all/")
    public ResultSet getLinkAll(){
        List<Rss> n = rssService.findAll();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveLink(@RequestBody @Valid NewLinkRequest newLinkRequest){
        Rss c = rssService.save(
                newLinkRequest.getId(),
                newLinkRequest.getIcon(),
                newLinkRequest.getTitle(),
                newLinkRequest.getLink(),
                newLinkRequest.getCategory(),
                newLinkRequest.getDescItem(),
                newLinkRequest.getSort());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",c);
    }
    @DeleteMapping(path = "/")
    public ResultSet delById(@RequestParam Long id){
        rssService.delOne(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
