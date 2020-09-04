package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public Article findById(Long id){
        return articleRepository.findAllById(id);
    }
    public Article findByTitle(String title){
        return articleRepository.findByTitle(title);
    }
}
