package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.TagColumn;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagColumnRepository extends JpaRepository<TagColumn, Long> {
    @EntityGraph(value = "TagColumnEntity", type = EntityGraph.EntityGraphType.LOAD)
    List<TagColumn> findAll();
}
