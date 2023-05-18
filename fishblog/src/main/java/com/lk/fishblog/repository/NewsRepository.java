package com.lk.fishblog.repository;
import com.lk.fishblog.model.News;

import java.util.List;

public interface NewsRepository extends BaseRepository<News, Long> {
    List<News> findAllByOrderByCreateTimeDesc();
}
