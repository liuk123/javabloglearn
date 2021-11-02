package com.lk.fishblog.repository;

import com.lk.fishblog.model.Authority;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorityRepository extends BaseRepository<Authority, Long> {
    List<Authority> findByNameIn(List<String> names);
}