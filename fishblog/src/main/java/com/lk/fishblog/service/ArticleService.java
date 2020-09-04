package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public Optional<Article> findById(Long id){
        return articleRepository.findById(id);
    }
    public Article save(Article a){
        return articleRepository.save(a);
    }
}
