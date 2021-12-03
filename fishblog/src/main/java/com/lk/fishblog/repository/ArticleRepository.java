package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article, Long> {
    Page<Article> findByAuthor_IdAndCategory_IdOrderByCreateTimeDesc(Long userId, Long categoryId, Pageable pageable);
    Page<Article> findByAuthor_IdOrderByCreateTimeDesc(Long userId, Pageable pageable);
    Page<Article> findDistinctByTagListInOrderByCreateTimeDesc(List<Tag> tagList, Pageable pageable);
    Page<Article> findByOrderByCreateTimeDesc(Pageable pageable);
//    void
//    @Query(value = "SELECT new Article(a.content) FROM Article a WHERE a.id=?1")
//    Article findContentById(Long id);
//    @Query(value = "SELECT new Article(a.author) FROM Article a WHERE a.id=?1")
//    Article findAuthorById(Long id);
}