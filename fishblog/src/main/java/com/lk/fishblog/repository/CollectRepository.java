package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Collect;
import com.lk.fishblog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectRepository extends JpaRepository<Collect, Collect.CollectEmbeddable> {
    Page<Collect> findByUser_Id(Long userId, Pageable pageable);
    Collect findByUser_IdAndArticle_Id(Long userId, Long articleId);
}
