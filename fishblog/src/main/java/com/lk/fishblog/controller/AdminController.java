package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewMenuRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.service.AuthorityService;
import com.lk.fishblog.service.RoleService;
import com.lk.fishblog.service.UserGroupService;
import com.lk.fishblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final UserService userService;
    final RoleService roleService;
    final AuthorityService authorityService;
    final UserGroupService userGroupService;


    public AdminController(UserService userService, RoleService roleService, AuthorityService authorityService, UserGroupService userGroupService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authorityService = authorityService;
        this.userGroupService = userGroupService;
    }

    /**
     * 添加角色
     * @param m 角色
     */
    @PostMapping(path = "/role/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addRoleByJson(@RequestBody @Valid NewMenuRequest m){

        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", null);
    }

    /**
     * 获取角色
     */
    @GetMapping(path="/role/")
    public ResultSet getRoleAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        roleService.findAll(pageNum,pageSize);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功");
    }

    /**
     * 删除角色
     * @param id 角色id
     */
    @DeleteMapping(path = "/role/{id}")
    public ResultSet delRoleById(@PathVariable Long id){
        roleService.delById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
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
     * 添加权限
     * @param m 权限
     */
    @PostMapping(path = "/auth/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addAuthByJson(@RequestBody @Valid NewMenuRequest m){
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", null);
    }

    /**
     * 获取权限
     */
    @GetMapping(path="/auth/")
    public ResultSet getAuthAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        authorityService.findAll(pageNum,pageSize);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功");
    }
    /**
     * 删除分组
     * @param id 角色id
     */
    @DeleteMapping(path = "/auth/{id}")
    public ResultSet delAuthById(@PathVariable Long id){
        authorityService.delById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

    /**
     * 获取用户
     */
    @GetMapping(path="/user/")
    public ResultSet getUserAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        userService.findAll(pageNum,pageSize);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功");
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
