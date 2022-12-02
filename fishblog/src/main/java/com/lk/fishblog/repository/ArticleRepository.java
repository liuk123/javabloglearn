package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article, Long> {
    Page<Article> findByAuthor_IdAndCategory_IdOrderByCreateTimeDesc(Long userId, Long categoryId, Pageable pageable);
    Page<Article> findByAuthor_IdOrderByCreateTimeDesc(Long userId, Pageable pageable);
    Page<Article> findByTagColumn_IdOrderByCreateTimeDesc(Long id, Pageable pageable);
    Page<Article> findByTag_IdInOrderByCreateTimeDesc(List<Long> ids, Pageable pageable);
    Page<Article> findByOrderByCreateTimeDesc(Pageable pageable);
//    @EntityGraph(value = "ArticleEntity", type = EntityGraph.EntityGraphType.FETCH)
    Article findFirstById(Long id);
//    void
//    @Query(value = "SELECT new Article(a.content) FROM Article a WHERE a.id=?1")
//    Article findContentById(Long id);
//    @Query(value = "SELECT new Article(a.author) FROM Article a WHERE a.id=?1")
//    Article findAuthorById(Long id);
}