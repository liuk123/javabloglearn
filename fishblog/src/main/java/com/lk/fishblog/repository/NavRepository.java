package com.lk.fishblog.repository;
import com.lk.fishblog.model.Nav;

import java.util.List;

public interface NavRepository extends BaseRepository<Nav, Long> {
    List<Nav> findByNavCategory_IdIn(List<Long> ids);
    List<Nav> findByNavCategory_Id(Long id);
}
