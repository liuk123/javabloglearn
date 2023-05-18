package com.lk.fishblog.repository;
import com.lk.fishblog.model.Rss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface RssRepository extends BaseRepository<Rss, Long> {
    @EntityGraph(value = "RssEntity", type = EntityGraph.EntityGraphType.FETCH)
    Page<Rss> findByOrderBySortDesc(Pageable pageable);
    @EntityGraph(value = "RssEntity", type = EntityGraph.EntityGraphType.LOAD)
    List<Rss> findAllByOrderBySortDesc();
}
