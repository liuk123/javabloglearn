package com.lk.fishblog.repository;

import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface RoleRepository extends BaseRepository<Role, Long> {

    @EntityGraph(value = "RoleEntity")
    Page<Role> findAll(Pageable pageable);
    @EntityGraph(value = "RoleEntity")
    List<Role> findAll();
}