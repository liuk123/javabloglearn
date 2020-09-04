package com.lk.fishblog.repository;

import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllById(Long id);
}
