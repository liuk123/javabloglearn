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
    @GetMapping(path="/navCategory/")
    public ResultSet getNavCategory(Authentication authentication){
        User u = (User) authentication.getPrincipal();
        List<NavCategory> n = navCategoryService.findNavCategory(u.getId());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }
    @GetMapping(path="/navItem/")
    public ResultSet getNav(Authentication authentication, @RequestParam List<Long> pids){
        List<Nav> n = navService.findNavByCIds(pids);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @PostMapping(path = "/importNav/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet getNavByUser(Authentication authentication, @RequestBody List<NewNavRequest> data){
        User u = (User) authentication.getPrincipal();
        List<NavCategory> nc = navCategoryService.findNavCategory(u.getId());
        List<Long> categoryIds = new ArrayList<>();
        List<String> categoryNames = new ArrayList<>();
        for(NavCategory item: nc){
            categoryIds.add(item.getId());
            categoryNames.add(item.getTitle());
        }
        List<Nav> n = navService.findNavByCIds(categoryIds);
        List<String> navNames = new ArrayList<>();
        for(Nav item: n){
            navNames.add(item.getTitle());
        }
        for(NewNavRequest item: data){
            getChild(item, navNames, categoryNames, u, null);
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    private void getChild(NewNavRequest data, List<String> navNames, List<String> categoryNames, User u, Long pId){
        if(data != null){
            Long p = null;
            if(data.getType().equals("link") && !navNames.contains(data.getTitle())){
                if(pId!=null){
                    NavCategory nc = new NavCategory(pId);
                    Nav n = navService.save(data.getId(),data.getSort(),data.getTitle(),data.getLink(),nc);
                    p = n.getId();
                }else{
                    Nav n = navService.save(data.getId(),data.getSort(),data.getTitle(),data.getLink(), null);
                    p = n.getId();
                }
            }else if(data.getType().equals("sub") && !categoryNames.contains(data.getTitle())){

                NavCategory nc = navCategoryService.save(data.getId(),pId,data.getSort(),data.getTitle(), u);
                p=nc.getId();

            }
            if(data.getChildren() != null){
                for(NewNavRequest item:data.getChildren()){
                    getChild(item, navNames, categoryNames, u, p);
                }
            }
        }
    }

    @PostMapping(path = "/navCategory/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNavCategory(@RequestBody @Valid NewNavCategoryRequest navCategory, Authentication authentication){
        if(authentication == null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "登录",null);
        }
        User u = (User) authentication.getPrincipal();
        navCategoryService.save(navCategory.getId(),navCategory.getPid(),navCategory.getSort(),navCategory.getTitle(), u);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",null);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNav(@RequestBody @Valid NewNavRequest navRequest){
        NavCategory nc = new NavCategory(navRequest.getNavCategoryId());
        navService.save(navRequest.getId(),navRequest.getSort(),navRequest.getTitle(),navRequest.getLink(),nc);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",null);
    }
}
