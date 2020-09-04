package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Reply;
import com.lk.fishblog.repository.ArticleRepository;
import com.lk.fishblog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableJpaRepositories
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

//    public Article insertOneArticle(Article a){
//        return articleRepository.save(a);
//    }
//    public List<Article> findAllByUserName(){
//        return articleRepository.findAllByUserName(1L);
//    }
    public Article findById(Long id){
        return articleRepository.findAllById(id);
    }
    public Article findByTitle(String title){
        return articleRepository.findByTitle(title);
    }

}
