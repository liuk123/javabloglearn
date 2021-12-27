package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewNavCategoryRequest;
import com.lk.fishblog.controller.request.NewNavRequest;
import com.lk.fishblog.model.Nav;
import com.lk.fishblog.model.NavCategory;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.NavCategoryService;
import com.lk.fishblog.service.NavService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nav")
@Slf4j
public class NavController {
    @Autowired
    NavService navService;
    @Autowired
    NavCategoryService navCategoryService;

    /**
     * 获取导航分类列表
     * @param authentication
     * @return
     */
    @GetMapping(path="/")
    public ResultSet getNavCategory(Authentication authentication){
        User u = (User) authentication.getPrincipal();
        List<NavCategory> n = navCategoryService.findNavCategory(u.getId());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }
    @GetMapping(path="/all/")
    public ResultSet getAll(Authentication authentication, @RequestParam Long pid){
        User u = (User) authentication.getPrincipal();
        List<NavCategory> n = navCategoryService.findByPid(u.getId(), pid);
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @PostMapping(path = "/navCategory/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNavCategory(@RequestBody @Valid NewNavCategoryRequest[] navCategoryList, Authentication authentication){
        if(authentication == null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "登录",null);
        }
        User u = (User) authentication.getPrincipal();
        List<NavCategory> nc = new ArrayList<>();
        for(NewNavCategoryRequest item: navCategoryList){
            nc.add(NavCategory.builder().id(item.getId()).pid(item.getPid()).sort(item.getSort()).title(item.getTitle()).user(u).build());
        }
        navCategoryService.saveAll(nc);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",null);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNav(@RequestBody @Valid NewNavRequest[] navRequestList){
        List<Nav> navs = new ArrayList<>();
        for(NewNavRequest item: navRequestList){
            NavCategory nc = new NavCategory(item.getNavCategoryId());
            navs.add(Nav.builder().id(item.getId()).sort(item.getSort()).title(item.getTitle()).link(item.getLink()).navCategory(nc).build());
        }
        navService.saveAll(navs);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",null);
    }
}
