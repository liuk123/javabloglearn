package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewMenuRequest;
import com.lk.fishblog.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 添加角色
     * @param m 角色
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addRoleByJson(@RequestBody @Valid NewMenuRequest m){

        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", null);
    }

    /**
     * 获取角色
     */
    @GetMapping(path="/")
    public ResultSet getRoleAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        roleService.findAll(pageNum,pageSize);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功");
    }

    /**
     * 删除角色
     * @param id 角色id
     */
    @DeleteMapping(path = "/{id}")
    public ResultSet delRoleById(@PathVariable Long id){
        roleService.delById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

}
