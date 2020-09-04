package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Transactional
    public Article findById(Long id){
        return articleRepository.getOne(id);
    }
    public Article save(String title, String content, User auther){
        return articleRepository.save(
            Article
                .builder()
                .title(title)
                .content(content)
                .author(auther)
                .build()
        );
    }
}
