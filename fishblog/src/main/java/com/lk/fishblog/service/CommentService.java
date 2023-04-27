package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.ArticleRepository;
import com.lk.fishblog.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Slf4j
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
    public List<Comment> findTop5ByArticleId(Long id){
        return  commentRepository.findTop5ByArticle_IdOrderByUpdateTimeDescIdAsc(id);
    }
    public Page<Comment> findByArticleId(Long id, int pageNum, int pageSize){
        return this.commentRepository.findByArticle_IdOrderByCreateTimeDesc(id, PageRequest.of(pageNum, pageSize));
    }

    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }
}
