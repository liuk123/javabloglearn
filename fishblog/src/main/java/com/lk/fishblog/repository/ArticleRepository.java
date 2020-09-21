package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends BaseRepository<Article, Long> {
    Page<Article> findAllByAuthor_Id(Long id, Pageable pageable);
    Page<Article> findAllBy(Pageable pageable);
}