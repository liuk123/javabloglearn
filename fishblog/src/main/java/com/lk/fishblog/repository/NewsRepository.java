package com.lk.fishblog.repository;
import com.lk.fishblog.model.News;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface NewsRepository extends BaseRepository<News, Long> {
    @EntityGraph(value = "NewsCategoryEntity", type = EntityGraph.EntityGraphType.FETCH)
    List<News> findAllByOrderByCreateTimeDesc();
    @EntityGraph(value = "NewsCategoryEntity", type = EntityGraph.EntityGraphType.FETCH)
    List<News> findAllByNewsCategory_id(Long id);
}
