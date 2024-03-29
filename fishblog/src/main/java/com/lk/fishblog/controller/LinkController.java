package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewLinkRequest;
import com.lk.fishblog.model.Link;
import com.lk.fishblog.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/link")
@Slf4j
@CacheConfig(cacheNames = "LinkCache")
public class LinkController {
    @Autowired
    LinkService linkService;
    /**
     * 获取书签分类
     * @param
     * @return
     */
    @GetMapping(path="/")
    @Cacheable
    public ResultSet getLinkAll(){
        List<Link> n = linkService.findAll();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveLink(@RequestBody @Valid NewLinkRequest newLinkRequest){
        Link c = linkService.save(
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
        linkService.delOne(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
