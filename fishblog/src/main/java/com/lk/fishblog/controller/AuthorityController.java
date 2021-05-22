package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewAuthorityRequest;
import com.lk.fishblog.model.Authority;
import com.lk.fishblog.service.AuthorityService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthorityController {

    final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * 添加权限
     * @param auth 权限
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addAuthByJson(@RequestBody @Valid NewAuthorityRequest auth){
        Authority authority = authorityService.save(auth.toAuthority());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加权限成功", authority);
    }

    /**
     * 获取权限
     */
    @GetMapping(path="/")
    public PageInfo<Authority> getAuth(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Page<Authority> u = authorityService.findAuth(pageIndex-1,pageSize);
        PageInfo<Authority> page = new PageInfo<Authority>(u);
        page.setPageSize(pageSize);
        return page;
    }
    /**
     * 获取所有权限
     */
    @GetMapping(path="/all/")
    public ResultSet getAllAuth(){
        List<Authority> auth = authorityService.findAllAuth();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"查询权限成功", auth);
    }
    /**
     * 删除权限
     * @param id 角色id
     */
    @DeleteMapping(path = "/{id}")
    public ResultSet delAuthById(@PathVariable Long id){
        authorityService.delById(id);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除权限成功");
    }
}
