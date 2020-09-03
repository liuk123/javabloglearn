package com.lk.fishblog.service;

import com.lk.fishblog.model.Comment;
import com.lk.fishblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;


    public Comment insertOneComment(Comment t){
        return commentRepository.save(t);
    }
    public List<Comment> findAllComment(){
        return commentRepository.findAll();
    }
}
