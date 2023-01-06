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
    public ResultSet getNav(@RequestParam List<Long> pids){
        List<Nav> n = navService.findNavByCIds(pids);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @PostMapping(path = "/importNav/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet importNav(Authentication authentication, @RequestBody List<NewNavRequest> data){
        User u = (User) authentication.getPrincipal();
        List<NavCategory> nc = navCategoryService.findNavCategory(u.getId());
        List<Nav> nList = new ArrayList<>();
        for(NavCategory item: nc){
            for(Nav subItem : item.getNavList()){
                subItem.setNavCategory(item);
                nList.add(subItem);
            }

        }
        for(NewNavRequest item: data){
            getChild(item, nList, nc, u, null);
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "导入成功");
    }

    private void getChild(NewNavRequest data, List<Nav> navList, List<NavCategory> categoryList, User u, Long pId){
        if(data != null){
            Long p = null;
            if(data.getType().equals("link")){
                boolean isGo = true;
                for(Nav item : navList){
                    if(item.getLink().equals(data.getLink())){
                        if(item.getNavCategory().getId().equals(pId)){
                            isGo = false;
                            p = item.getId();
                            break;
                        }
                        data.setId(item.getId());
                        break;
                    }
                }
                if(pId!=null && isGo){
                    NavCategory nc = new NavCategory(pId);
                    Nav n = navService.save(data.getId(),data.getSort(),data.getTitle(),data.getLink(),nc);
                    p = n.getId();
                }else if(isGo){
                    Nav n = navService.save(data.getId(),data.getSort(),data.getTitle(),data.getLink(), null);
                    p = n.getId();
                }
            }else if(data.getType().equals("sub")){
                boolean isGo = true;
                for(NavCategory item : categoryList){
                    if(item.getTitle().equals(data.getTitle())){
                        if((item.getPid() == null && pId == null)||(item.getPid() != null && item.getPid().equals(pId))){
                            isGo = false;
                            p = item.getId();
                            break;
                        }
                        data.setId(item.getId());
                        break;
                    }
                }
                if(isGo){
                    NavCategory nc = navCategoryService.save(data.getId(),pId,data.getSort(),data.getTitle(), u);
                    p=nc.getId();
                }
            }
            if(data.getChildren() != null){
                for(NewNavRequest item:data.getChildren()){
                    getChild(item, navList, categoryList, u, p);
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
        NavCategory nc = navCategoryService.save(navCategory.getId(),navCategory.getPid(),navCategory.getSort(),navCategory.getTitle(), u);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",nc);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNav(@RequestBody @Valid NewNavRequest navRequest){
        NavCategory nc = new NavCategory(navRequest.getNavCategoryId());
        Nav n = navService.save(navRequest.getId(),navRequest.getSort(),navRequest.getTitle(),navRequest.getLink(),nc);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",n);
    }

    @DeleteMapping(path = "/{id}")
    public ResultSet delById(@PathVariable Long id){
        navService.delOne(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
    @DeleteMapping(path = "/navCategory/{id}")
    public ResultSet delNavCategoryById(@PathVariable Long id){
        navCategoryService.delOne(id);
        List<NavCategory> ncList = navCategoryService.findByPid(id);
        for(NavCategory item: ncList){
            item.setPid(null);
        }
        navCategoryService.saveAll(ncList);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
