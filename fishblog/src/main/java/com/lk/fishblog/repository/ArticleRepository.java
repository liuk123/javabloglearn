package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article, Long> {
    Page<Article> findByTypeInAndAuthor_IdAndCategory_IdOrderByCreateTimeDesc(List<Long> types, Long userId, Long categoryId, Pageable pageable);
    Page<Article> findByTypeInAndAuthor_IdOrderByCreateTimeDesc(List<Long> types, Long userId, Pageable pageable);
    Page<Article> findByTypeInAndTagColumn_IdOrderByCreateTimeDesc(List<Long> types, Long id, Pageable pageable);
    Page<Article> findByTypeInAndTag_IdInOrderByCreateTimeDesc(List<Long> types, List<Long> ids, Pageable pageable);
    Page<Article> findByTypeInOrderByCreateTimeDesc(List<Long> types, Pageable pageable);
    Article findFirstById(Long id);
//    void
//    @Query(value = "SELECT new Article(a.content) FROM Article a WHERE a.id=?1")
//    Article findContentById(Long id);
//    @Query(value = "SELECT new Article(a.author) FROM Article a WHERE a.id=?1")
//    Article findAuthorById(Long id);
}