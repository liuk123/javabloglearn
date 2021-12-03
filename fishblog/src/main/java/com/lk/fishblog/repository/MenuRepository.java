package com.lk.fishblog.repository;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Menu;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu, Long> {
    Menu findFirstById(Long id);
    List<Menu> findByAuthorityListInOrderBySortAsc(List<Authority> authorityList);
    List<Menu> findByAuthorityListNotInOrderBySortAsc(List<Authority> authorityList);
}