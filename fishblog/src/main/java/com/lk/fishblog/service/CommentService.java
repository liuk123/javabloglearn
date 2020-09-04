package com.lk.fishblog.service;

import com.lk.fishblog.model.Comment;
import com.lk.fishblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment c){
        return commentRepository.save(c);
    }
    public Optional<Comment> findById(Long id){
        return commentRepository.findById(id);
    }
}
