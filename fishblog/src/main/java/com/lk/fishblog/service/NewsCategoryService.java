package com.lk.fishblog.service;

import com.lk.fishblog.model.NewsCategory;
import com.lk.fishblog.repository.NewsCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class NewsCategoryService {
    @Autowired
    NewsCategoryRepository newsCategoryRepository;

    public NewsCategory save(Long id, Long sort, String title){
        return newsCategoryRepository.save(
                NewsCategory.builder().id(id).sort(sort).title(title).build()
        );
    }
    public List<NewsCategory> findAll(){
        return newsCategoryRepository.findAllByOrderBySortDesc();
    }

    public Optional<NewsCategory> findById(long id){
        return newsCategoryRepository.findById(id);
    }
    public void delOne(Long id){
        newsCategoryRepository.deleteFirstById(id);
    }
}
