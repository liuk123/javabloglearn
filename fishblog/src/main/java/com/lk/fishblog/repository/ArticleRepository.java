package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article, Long> {
    Page<Article> findAllByAuthor_Id(Long id, Pageable pageable);
    Page<Article> findDistinctByTagListIn(List<Tag> tagList, Pageable pageable);

//    @Query(value = "SELECT new Article(a.content) FROM Article a WHERE a.id=?1")
//    Article findContentById(Long id);
//    @Query(value = "SELECT new Article(a.author) FROM Article a WHERE a.id=?1")
//    Article findAuthorById(Long id);
}