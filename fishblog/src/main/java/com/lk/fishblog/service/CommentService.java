package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment save(String content, User fromUser, Article article){
        return commentRepository.save(
            Comment
                .builder()
                .content(content)
                .fromUser(fromUser)
                .article(article)
                .build()
        );
    }
    public Comment findById(Long id){
        return commentRepository.getOne(id);
    }

    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }
}
