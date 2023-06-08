package com.lk.fishblog.repository;
import com.lk.fishblog.model.BookmarkCategory;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface BookmarkCategoryRepository extends BaseRepository<BookmarkCategory, Long> {
    @EntityGraph(value = "BookmarkCategoryEntity", type = EntityGraph.EntityGraphType.LOAD)
    List<BookmarkCategory> findByOrderBySort();
    @EntityGraph(value = "BookmarkCategoryEntity", type = EntityGraph.EntityGraphType.LOAD)
    List<BookmarkCategory> findByPidOrderBySort(Long pid);
    @EntityGraph(value = "BookmarkCategoryEntity", type = EntityGraph.EntityGraphType.LOAD)
    List<BookmarkCategory> findByIdOrderBySort(Long pid);
    void  deleteFirstById(Long id);

}
