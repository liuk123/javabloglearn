package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewLinkRequest;
import com.lk.fishblog.model.Friend;
import com.lk.fishblog.model.Link;
import com.lk.fishblog.service.FriendService;
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
@RequestMapping("/friend")
@Slf4j
@CacheConfig(cacheNames = "FriendCache")
public class FriendController {
    @Autowired
    FriendService friendService;

    /**
     * 获取书签分类
     * @param
     * @return
     */
    @GetMapping(path="/")
    public ResultSet getFriend(){
        List<Friend> n = friendService.findAll();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveLink(@RequestBody @Valid NewLinkRequest newLinkRequest){
        Friend c = friendService.save(
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
        friendService.delOne(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
