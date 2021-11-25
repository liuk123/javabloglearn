package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Category;
import com.lk.fishblog.model.Tag;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.CategoryRepository;
import com.lk.fishblog.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category save(Long id, String name, User user, List<Article> articleList){
        return categoryRepository.save(
            Category.builder().id(id).name(name).author(user).articleList(articleList).build()
        );
    }
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
    public List<Category> findByAuthor(Long id){ return categoryRepository.findByAuthor_Id(id); }
}
