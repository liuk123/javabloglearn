package com.lk.fishblog.repository;

import com.lk.fishblog.model.Comment;

import java.util.List;


public interface CommentRepository extends BaseRepository<Comment, Long> {
    List<Comment> findTop5ByArticle_IdOrderByUpdateTimeDescIdAsc(Long id);
}
