package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewMenuRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.UserGroupService;
import com.lk.fishblog.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final UserService userService;
    final UserGroupService userGroupService;

    public AdminController(UserService userService, UserGroupService userGroupService) {
        this.userService = userService;
        this.userGroupService = userGroupService;
    }

    /**
     * 添加分组
     * @param m 分组
     */
    @PostMapping(path = "/userGroup/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addUserGroupByJson(@RequestBody @Valid NewMenuRequest m){
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", null);
    }

    /**
     * 获取分组
     */
    @GetMapping(path="/userGroup/")
    public ResultSet getUserGroupAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        userGroupService.findAll(pageNum,pageSize);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功");
    }
    /**
     * 删除分组
     * @param id 角色id
     */
    @DeleteMapping(path = "/userGroup/{id}")
    public ResultSet delUserGroupById(@PathVariable Long id){
        userGroupService.delById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

    /**
     * 获取用户
     */
    @GetMapping(path="/user/")
    public PageInfo<User> getUserAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        Page<User> u = userService.findAll(pageNum-1,pageSize);
        PageInfo<User> page = new PageInfo(u);
        page.setPageSize(pageSize);
        return page;
    }
    /**
     * 删除用户
     * @param id 用户id
     */
    @DeleteMapping(path = "/user/{id}")
    public ResultSet delUserById(@PathVariable Long id){
        userService.delById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

}
