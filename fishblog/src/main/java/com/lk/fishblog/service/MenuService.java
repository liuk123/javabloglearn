package com.lk.fishblog.service;

import com.lk.fishblog.model.*;
import com.lk.fishblog.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Menu> getMenuListByPid(Long pid, int pageNum, int pageSize){
        return menuRepository.findAllByPid(pid, PageRequest.of(pageNum, pageSize));
    }public Page<Menu> getMenuListByPidIsNull(int pageNum, int pageSize){
        return menuRepository.findAllByPidIsNull(PageRequest.of(pageNum, pageSize));
    }
    public void saveMenu(Long id, Long pid, Long sort, String title, String type, String icon, String disabled, String selected, String open, String route, String link, Boolean isMenuShow, Boolean isBreadcrumbShow, String meta, List<Authority> authorityList){
        menuRepository.save(
                Menu
                        .builder()
                        .id(id)
                        .pid(pid)
                        .sort(sort)
                        .title(title)
                        .type(type)
                        .icon(icon)
                        .disabled(disabled)
                        .selected(selected)
                        .open(open)
                        .route(route)
                        .link(link)
                        .isMenuShow(isMenuShow)
                        .isBreadcrumbShow(isBreadcrumbShow)
                        .meta(meta)
                        .authorityList(authorityList)
                        .build()
        );
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

    public  List<Menu> getMenuByAuth(List<Authority> authorityList){
        return menuRepository.findByAuthorityListInOrderBySortAsc(authorityList);
    }
    public  List<Menu> getMenuByAuthNot(List<Authority> authorityList){
        return menuRepository.findByAuthorityListNotInOrderBySortAsc(authorityList);
    }

}