package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewLinkRequest;
import com.lk.fishblog.model.Speak;
import com.lk.fishblog.service.SpeakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/speak")
@Slf4j
public class SpeakController {
    @Autowired
    SpeakService speakService;

    /**
     *
     * @param
     * @return
     */
    @GetMapping(path="/")
    public PageInfo<Speak> get(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Page<Speak> n = speakService.findByPage(pageIndex-1, pageSize);
        PageInfo<Speak> page = new PageInfo<>(n);
        return page;
    }

    @GetMapping(path="/random/")
    public ResultSet getRandomFriend(@RequestParam(required = false) Long total){
        Speak n = speakService.random(total);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveLink(@RequestBody @Valid NewLinkRequest newLinkRequest){
        Speak c = speakService.save(
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
        speakService.delOne(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
