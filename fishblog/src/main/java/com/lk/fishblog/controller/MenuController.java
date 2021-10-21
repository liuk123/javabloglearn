package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.*;
import com.lk.fishblog.controller.request.NewMenuRequest;
import com.lk.fishblog.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/menu")
public class MenuController {

    final UserService userService;
    final MenuService menuService;

    public MenuController(UserService userService, MenuService menuService) {
        this.userService = userService;
        this.menuService = menuService;
    }

    /**
     * 添加菜单
     * @param m 菜单
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewMenuRequest m){
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
                m.getAuthorityList());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", null);
    }

    /**
     * 获取菜单
     */
    @GetMapping(path="/")
    public ResultSet getMenuAll(){
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功", menuService.getMenuList());
    }

}
