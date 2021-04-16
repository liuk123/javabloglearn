package com.lk.fishblog.service;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.model.*;
import com.lk.fishblog.repository.MenuRepository;
import com.lk.fishblog.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public Menu getMenuById(Long id){
        return menuRepository.findFirstById(id);
    }
    public List<Menu> getMenuList(){
        return menuRepository.findAll();
    }
    public Menu saveMenu(Long id,String parentId, String title, String type, String icon, String disabled, String selected, String open, String route, String link, String permission, List<Role> roleList){
        Menu save = menuRepository.save(
            Menu
                .builder()
                .id(id)
                .parentId(parentId)
                .title(title)
                .type(type)
                .icon(icon)
                .disabled(disabled)
                .selected(selected)
                .open(open)
                .route(route)
                .link(link)
                .permission(permission)
                .build()
        );
        return save;
    }

    public static List<Menu> tree(List<Menu> listByRoleId,List<Menu> menuList){
        List<Long> collect = listByRoleId.stream().map(Menu::getId).collect(Collectors.toList());
        List<Long> collect1 = menuList.stream().map(Menu::getId).collect(Collectors.toList());
        for (Long item : collect) {// 遍历list2
            if (collect1.contains(item)) {// 如果存在这个数
                Menu menuDto = menuList.get((int) (item-1));
                menuList.set((int) (item-1),menuDto);
            }
        }
        return menuList;
    }
}