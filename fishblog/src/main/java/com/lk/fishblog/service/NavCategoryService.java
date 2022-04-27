package com.lk.fishblog.service;

import com.lk.fishblog.model.*;
import com.lk.fishblog.repository.NavCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class NavCategoryService {
    @Autowired
    NavCategoryRepository navCategoryRepository;

    public NavCategory save(Long id, Long pid, Long sort, String title, User u){
        return navCategoryRepository.save(
                NavCategory.builder().id(id).pid(pid).sort(sort).title(title).user(u).build()
        );
    }
    public List<NavCategory> findNavCategory(Long userId){
        return navCategoryRepository.findByUser_IdOrderBySort(userId);
    }

    public List<NavCategory> findByPid(long pid){
        return navCategoryRepository.findByPid(pid);
    }
    public void delOne(Long id){
        navCategoryRepository.deleteFirstById(id);
    }
    public void saveAll(List<NavCategory> ncList){
        navCategoryRepository.saveAll(ncList);
    }
}