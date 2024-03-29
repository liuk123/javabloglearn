package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.*;
import com.lk.fishblog.controller.request.NewMenuRequest;
import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Menu;
import com.lk.fishblog.service.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/menu")
@CacheConfig(cacheNames = "MenuCache")
public class MenuController {

    final UserService userService;
    final MenuService menuService;
    final AuthorityService authorityService;

    public MenuController(UserService userService, MenuService menuService, AuthorityService authorityService) {
        this.userService = userService;
        this.menuService = menuService;
        this.authorityService = authorityService;
    }

    /**
     * 添加菜单
     * @param m 菜单
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewMenuRequest m){
        List<Authority> authList = new ArrayList<>();
        for(Long val: m.getAuthorityIds()){
            authList.add(new Authority(val));
        }
        menuService.saveMenu(
                m.getId(),
                m.getPid(),
                m.getSort(),
                m.getTitle(),
                m.getType(),
                m.getIcon(),
                m.getDisabled(),
                m.getSelected(),
                m.getOpen(),
                m.getRoute(),
                m.getLink(),
                m.getIsMenuShow(),
                m.getIsBreadcrumbShow(),
                m.getMeta(),
                authList);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", null);
    }

    /**
     * 获取菜单
     */
    @GetMapping(path="/all/")
    public PageInfo<Menu> getMenuAll(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = false) Long pid){
        Page<Menu> a;
        if(pid==null){
            a = menuService.getMenuListByPidIsNull(pageIndex-1, pageSize);
        }else{
            a = menuService.getMenuListByPid(pid,pageIndex-1, pageSize);
        }
        for(Menu menu: a.getContent()){
            List<Authority> auths = new ArrayList<>(menu.getAuthorityList());
            menu.setAuthorityList(auths);
        }
        return new PageInfo<>(a);
    }

    /**
     * 获取菜单 根据权限
     * @param
     * @return
     */
    @GetMapping(path="/")
    public ResultSet getMenuByAuth(Authentication authentication){

        List<Menu> m;
        if(authentication != null){
            Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
            List<String> nameList = new ArrayList<>();
            for(GrantedAuthority auth: auths){
                nameList.add(auth.getAuthority());
            }
            List<Authority> authList = authorityService.findByNames(nameList);
            m = menuService.getMenuByAuth(authList);
        }else{
            List<Authority> authList = authorityService.findAllAuth();
            m = menuService.getMenuByAuthNot(authList);
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功", m);
    }

}
