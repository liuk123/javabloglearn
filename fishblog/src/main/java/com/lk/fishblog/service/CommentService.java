package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.ArticleRepository;
import com.lk.fishblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

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
    public String saveA(String content, User fromUser, Article article){
        Comment comment = Comment
                .builder()
                .content(content)
                .fromUser(fromUser)
                .article(article)
                .build();
        articleRepository.save(Article.builder().commentList(Collections.singletonList(comment)).build());
        return "success";
    }
    public Comment findById(Long id){
        return commentRepository.getOne(id);
    }

    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }
}
