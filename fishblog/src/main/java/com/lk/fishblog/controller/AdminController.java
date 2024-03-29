package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewUserGroupRequest;
import com.lk.fishblog.controller.request.NewUserRequest;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.Tag;
import com.lk.fishblog.model.User;
import com.lk.fishblog.model.UserGroup;
import com.lk.fishblog.service.UserGroupService;
import com.lk.fishblog.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
     * @param ug 分组
     */
    @PostMapping(path = "/userGroup/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addUserGroupByJson(@RequestBody @Valid NewUserGroupRequest ug){
        List<Role> roleList = new ArrayList<>();
        if(ug.getRoleIds() != null){
            for(Long val: ug.getRoleIds()){
                roleList.add(new Role(val));
            }
        }
        UserGroup userGroup = userGroupService.save(ug.getId(),ug.getName(),ug.getDescription(), roleList);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加分组成功", userGroup);
    }

    /**
     * 获取分组
     */
    @GetMapping(path="/userGroup/")
    public PageInfo<UserGroup> getUserGroups(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Page<UserGroup> u = userGroupService.findUserGroups(pageIndex-1,pageSize);
        PageInfo<UserGroup> page = new PageInfo<UserGroup>(u);
        page.setPageSize(pageSize);
        return page;
    }
    /**
     * 获取所有分组
     */
    @GetMapping(path="/allUserGroup/")
    public ResultSet getAllUserGroups(){
        List<UserGroup> userGroups = userGroupService.findAllUserGroups();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取所有分组成功", userGroups);
    }
    /**
     * 删除分组
     * @param id 角色id
     */
    @DeleteMapping(path = "/userGroup/{id}")
    public ResultSet delUserGroupById(@PathVariable Long id){
        userGroupService.delById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除分组成功");
    }

    /**
     * 获取用户
     */
    @GetMapping(path="/user/")
    public PageInfo<User> getUserAll(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Page<User> u = userService.findAll(pageIndex-1,pageSize);
        for(User val: u.getContent()){
            if(val.getUserGroupList().isEmpty()){
                val.setUserGroupList(new ArrayList<>());
            }else{
                val.setUserGroupList(val.getUserGroupList());
            }
        }
        PageInfo<User> page = new PageInfo<User>(u);
        page.setPageSize(pageSize);
        return page;
    }
    /**
     * 编辑用户
     * @param u 分组
     */
    @PostMapping(path = "/user/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addUserByJson(@RequestBody @Valid NewUserRequest u){
        List<Role> roleList = new ArrayList<>();
        if(u.getRoleIds()!=null){
            for(Long val: u.getRoleIds()){
                roleList.add(new Role(val));
            }
        }
        List<UserGroup> userGroupList = new ArrayList<>();
        for(Long val: u.getUserGroupIds()){
            userGroupList.add(new UserGroup(val));
        }
        User user = userService.save(u.getId(), u.getPassword(), u.getUsername(), u.getPhone(), u.getEmail(), u.getAvatar(), roleList,userGroupList, u.getAccountNonExpired(),u.getAccountNonLocked(),u.getCredentialsNonExpired(),u.getEnabled());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加用户成功", user);
    }
    /**
     * 删除用户
     * @param id 用户id
     */
    @DeleteMapping(path = "/user/{id}")
    public ResultSet delUserById(@PathVariable Long id){
        userService.delById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除用户成功");
    }

}
