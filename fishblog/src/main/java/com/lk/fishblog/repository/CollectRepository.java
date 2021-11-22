package com.lk.fishblog.repository;

import com.lk.fishblog.model.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectRepository extends JpaRepository<Collect, Long> {
    Page<Collect> findByUser_Id(Long userId, Pageable pageable);
    Long findUser_IdByUser_IdAndArticle_Id(Long userId, Long ArticleId);
}
