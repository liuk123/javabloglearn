package com.lk.fishblog.repository;

import com.lk.fishblog.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CommentRepository extends BaseRepository<Comment, Long> {
    List<Comment> findTop5ByArticle_IdOrderByUpdateTimeDescIdAsc(Long id);
    Page<Comment> findByArticle_IdOrderByCreateTimeDesc(Long id, Pageable pageable);
}
