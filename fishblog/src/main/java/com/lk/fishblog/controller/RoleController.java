package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewRoleRequest;
import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 添加角色
     * @param r 角色
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addRoleByJson(@RequestBody @Valid NewRoleRequest r){
        List<Authority> authorityList = new ArrayList<>();
        for(Long val: r.getAuthorityIds()){
            authorityList.add(new Authority(val));
        }
        Role role = roleService.save(r.getId(),r.getName(),r.getDescription(),authorityList);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", role);
    }

    /**
     * 获取角色
     */
    @GetMapping(path="/")
    public PageInfo<Role> getRole(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Page<Role> u = roleService.findRoles(pageIndex-1,pageSize);
        PageInfo<Role> page = new PageInfo<Role>(u);
        page.setPageSize(pageSize);
        return page;
    }
    /**
     * 获取所有角色
     */
    @GetMapping(path="/all/")
    public ResultSet getAllRole(){
        List<Role> u = roleService.findAllRoles();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取角色", u);
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
