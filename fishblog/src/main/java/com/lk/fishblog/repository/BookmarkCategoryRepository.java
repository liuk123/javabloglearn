package com.lk.fishblog.repository;
import com.lk.fishblog.model.BookmarkCategory;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface BookmarkCategoryRepository extends BaseRepository<BookmarkCategory, Long> {
    List<BookmarkCategory> findByOrderBySort();
    List<BookmarkCategory> findByPidOrderBySort(Long pid);
    void  deleteFirstById(Long id);

}
