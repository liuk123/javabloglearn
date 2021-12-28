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

    public void saveAll(List<NavCategory> navCategoryList){
        navCategoryRepository.saveAll(navCategoryList);
    }
    public List<NavCategory> findNavCategory(Long userId){
        return navCategoryRepository.findByUser_Id(userId);
    }

    public List<NavCategory> findByPid(Long userId, long pid){
        return navCategoryRepository.findByUser_IdAndPid(userId, pid);
    }
    public NavCategory findOne(Long id){
        return navCategoryRepository.findFirstById(id);
    }
}
