package com.lk.fishblog.repository;

import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyRepository extends BaseRepository<Reply, Long> {
    Page<Reply> findByComment_IdOrderByCreateTimeDesc(Long id, Pageable pageable);
}
