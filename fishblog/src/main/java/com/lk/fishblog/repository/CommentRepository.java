package com.lk.fishblog.repository;

import com.lk.fishblog.model.Comment;

import java.util.List;


public interface CommentRepository extends BaseRepository<Comment, Long> {
    List<Comment> findTop3ByArticle_IdOrderByUpdateTimeDescIdAsc(Long id);
    List<Comment> findTop3AndReplyListByArticle_IdOrderByUpdateTimeDescIdAsc(Long id);
}
