package com.lk.fishblog.repository;
import com.lk.fishblog.model.NavCategory;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface NavCategoryRepository extends BaseRepository<NavCategory, Long> {
    @EntityGraph(value = "NavCategoryEntity", type = EntityGraph.EntityGraphType.LOAD)
    List<NavCategory> findByUser_IdOrderBySort(Long userId);
    @EntityGraph(value = "NavCategoryEntity", type = EntityGraph.EntityGraphType.LOAD)
    List<NavCategory> findByPid(Long pid);
    void  deleteFirstById(Long id);
}
