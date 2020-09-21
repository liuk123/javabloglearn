package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Tag;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public Article findById(Long id){
        return articleRepository.getOne(id);
    }
    @Cacheable
    public Page<Article> findByAuthor(Long id, int pageNum, int pageSize){
        return this.articleRepository.findAllByAuthor_Id(id, PageRequest.of(pageNum, pageSize));
    }
    public Page<Article> findAll(int pageNum, int pageSize){
        return this.articleRepository.findAllBy(PageRequest.of(pageNum, pageSize));
    }
    public Article save(String title, String content, List<Tag> tagList, User auther){
        return articleRepository.save(
            Article
                .builder()
                .title(title)
                .content(content)
                .tagList(new ArrayList<>(tagList))
                .author(auther)
                .build()
        );
    }
    public void deleteById(Long id){
        articleRepository.deleteById(id);
    }

}
