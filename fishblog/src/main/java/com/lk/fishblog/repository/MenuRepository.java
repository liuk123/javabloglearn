package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Menu;
import com.lk.fishblog.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu, Long> {
    Menu findFirstById(Long id);
    List<Menu> findDistinctByAuthorityListIn(List<Authority> authorityList);
}