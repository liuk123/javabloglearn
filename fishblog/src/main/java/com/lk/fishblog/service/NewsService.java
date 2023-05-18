package com.lk.fishblog.service;

import com.lk.fishblog.model.News;
import com.lk.fishblog.repository.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class NewsService {

    @Autowired
    NewsRepository newsRepository;

    public void save(List<News> news){
        newsRepository.saveAll(news);
    }
    public void deleteAll(){
        newsRepository.deleteAllInBatch();
    }
    public List<News> findAll(){
        return newsRepository.findAllByOrderByCreateTimeDesc();
    }
}
