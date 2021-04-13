package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.*;
import com.lk.fishblog.controller.request.NewArticleRequest;
import com.lk.fishblog.controller.request.NewMenuRequest;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Tag;
import com.lk.fishblog.model.User;
import com.lk.fishblog.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        menuService.saveMenu(m.getId(),m.getParentId(),m.getTitle(),m.getType(),m.getIcon(),m.getDisabled(),m.getSelected(),m.getOpen(),m.getRoute(),m.getLink(),m.getPermission(),m.getRoleList());
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
