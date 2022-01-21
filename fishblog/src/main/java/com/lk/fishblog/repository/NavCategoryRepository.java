package com.lk.fishblog.repository;
import com.lk.fishblog.model.NavCategory;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface NavCategoryRepository extends BaseRepository<NavCategory, Long> {
    @EntityGraph(value = "NavCategoryEntity", type = EntityGraph.EntityGraphType.FETCH)
    List<NavCategory> findByUser_Id(Long userId);
    @EntityGraph(value = "NavCategoryEntity", type = EntityGraph.EntityGraphType.FETCH)
    List<NavCategory> findByUser_IdAndPid(Long userId, Long pid);
    @EntityGraph(value = "NavCategoryEntity", type = EntityGraph.EntityGraphType.FETCH)
    NavCategory findFirstById(Long id);
}
