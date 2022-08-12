package com.lk.fishblog.repository;
import com.lk.fishblog.model.Link;

import java.util.List;

public interface LinkRepository extends BaseRepository<Link, Long> {
    List<Link> findAllByOrderByCreateTimeDesc();
    List<Link> findAllByTypeOrderBySortDesc(String type);
}
