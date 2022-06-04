package com.lk.fishblog.repository;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu, Long> {
    Menu findFirstById(Long id);
    @EntityGraph(value = "MenuEntity", type = EntityGraph.EntityGraphType.FETCH)
    List<Menu> findByAuthorityListInOrderBySortAsc(List<Authority> authorityList);
    @EntityGraph(value = "MenuEntity", type = EntityGraph.EntityGraphType.FETCH)
    List<Menu> findByAuthorityListNotInOrderBySortAsc(List<Authority> authorityList);

    Page<Menu> findAllByPid(Long pid, Pageable page);
    Page<Menu> findAllByPidIsNull(Pageable page);
}