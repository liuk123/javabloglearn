package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewMenuRequest;
import com.lk.fishblog.service.MenuService;
import com.lk.fishblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final UserService userService;
    final MenuService menuService;

    public AdminController(UserService userService, MenuService menuService) {
        this.userService = userService;
        this.menuService = menuService;
    }

    /**
     * 添加角色
     * @param m 菜单
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestBody @Valid NewMenuRequest m){
        menuService.saveMenu(m.getId(),m.getParentId(),m.getTitle(),m.getType(),m.getIcon(),m.getDisabled(),m.getSelected(),m.getOpen(),m.getRoute(),m.getLink());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"添加成功", null);
    }

    /**
     * 获取角色
     */
    @GetMapping(path="/")
    public ResultSet getMenuAll(){
        return new ResultSet(ResultSet.RESULT_CODE_TRUE,"获取成功", menuService.getMenuList());
    }

}
