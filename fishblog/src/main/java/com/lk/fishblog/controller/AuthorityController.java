package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewMenuRequest;
import com.lk.fishblog.service.AuthorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthorityController {

    final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * 添加权限
     * @param m 权限
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addAuthByJson(@RequestBody @Valid NewMenuRequest m){
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", null);
    }

    /**
     * 获取权限
     */
    @GetMapping(path="/")
    public ResultSet getAuthAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        authorityService.findAll(pageNum,pageSize);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功");
    }
    /**
     * 删除分组
     * @param id 角色id
     */
    @DeleteMapping(path = "/{id}")
    public ResultSet delAuthById(@PathVariable Long id){
        authorityService.delById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
