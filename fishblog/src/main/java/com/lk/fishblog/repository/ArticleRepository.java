package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findAllById(Long id);
    Article findByTitle(String title);
//    List<Article> findAllByUserName(Long id);
}
