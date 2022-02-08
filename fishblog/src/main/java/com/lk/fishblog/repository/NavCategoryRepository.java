package com.lk.fishblog.repository;
import com.lk.fishblog.model.NavCategory;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface NavCategoryRepository extends BaseRepository<NavCategory, Long> {
    List<NavCategory> findByUser_IdOrderBySort(Long userId);
    List<NavCategory> findByPid(Long pid);
    void  deleteFirstById(Long id);
}
