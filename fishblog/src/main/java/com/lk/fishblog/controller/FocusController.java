package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewFocusRequest;
import com.lk.fishblog.controller.request.NewUserRequest;
import com.lk.fishblog.controller.request.NewUserResponse;
import com.lk.fishblog.model.Collect;
import com.lk.fishblog.model.Focus;
import com.lk.fishblog.model.User;
import com.lk.fishblog.security.MyPasswordEncoder;
import com.lk.fishblog.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/focus")
@Slf4j
public class FocusController {
    @Autowired
    FocusService focusService;

    /**
     * 获取关注列表
     * @param authentication
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping(path="/")
    public PageInfo<Focus> getFocus(Authentication authentication, @RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        User u = (User) authentication.getPrincipal();
        Page<Focus> f = focusService.findFocusList(u.getId(), pageIndex-1, pageSize);
        PageInfo<Focus> page = new PageInfo(f);
        return  page;
    }
    /**
     * 关注某人
     * @param focusRequest
     * @param authentication
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveFocus(@RequestBody @Valid NewFocusRequest focusRequest, Authentication authentication){
        if(authentication == null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "登录",null);
        }
        User u = (User) authentication.getPrincipal();
        User u1 = new User(focusRequest.getUserId());
        focusService.saveFocus(u, u1);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "关注成功",null);
    }
    /**
     * 取消关注
     * @param userId 用户id
     */
    @DeleteMapping(path = "/{id}")
    public ResultSet delCollect(@PathVariable Long userId, Authentication authentication){
        if(authentication == null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "登录",null);
        }
        User u = (User) authentication.getPrincipal();
        User u1 = new User(userId);
        Focus f = new Focus(u, u1);
        focusService.deleteFocus(f);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

    /**
     * 判断是否关注
     * @param authentication
     * @param userId
     * @return
     */
    @GetMapping(path="/is")
    public ResultSet getFocusIs(Authentication authentication, @RequestParam Long userId){
        User u = (User) authentication.getPrincipal();
        Focus f = focusService.findFocusById(u.getId(), userId);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "关注", f == null);
    }
}
