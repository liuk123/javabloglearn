package com.lk.fishblog.repository;
import com.lk.fishblog.model.Category;
import com.lk.fishblog.model.User;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Category, Long> {
    List<Category> findByAuthor_Id(Long userId);
}
