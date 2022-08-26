package com.lk.fishblog.repository;
import com.lk.fishblog.model.NewsCategory;

import java.util.List;

public interface NewsCategoryRepository extends BaseRepository<NewsCategory, Long> {
    List<NewsCategory> findAllByOrderBySortDesc();
    void  deleteFirstById(Long id);
}
