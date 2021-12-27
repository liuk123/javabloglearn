package com.lk.fishblog.repository;
import com.lk.fishblog.model.NavCategory;

import java.util.List;

public interface NavCategoryRepository extends BaseRepository<NavCategory, Long> {
    List<NavCategory> findByUser_Id(Long userId);
    List<NavCategory> findByUser_IdAndPid(Long userId, Long pid);
    List<NavCategory> findNavTop3ByUserId(Long userId);
}
