package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.ArticleRepository;
import com.lk.fishblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@Slf4j
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserRepository userRepository;

    public Article findById(Long id){
        return articleRepository.getOne(id);
    }
    public Page<Article> findByAuthor(Long id,int page, int size ){
        return this.articleRepository.findAllByAuthor_Id(id, PageRequest.of(page,size));
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
    public String saveA(String title, String content, User auther){
        Article article = Article
                    .builder()
                    .title(title)
                    .content(content)
                    .author(auther)
                    .build();
        userRepository.save(User.builder().articleList(Collections.singletonList(article)).build());
        return "success";
    }
    public void deleteById(Long id){
        articleRepository.deleteById(id);
    }

}
