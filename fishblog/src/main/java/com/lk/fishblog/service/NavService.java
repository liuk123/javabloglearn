package com.lk.fishblog.service;

import com.lk.fishblog.model.Nav;
import com.lk.fishblog.model.NavCategory;

import com.lk.fishblog.repository.NavRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class NavService {
    @Autowired
    NavRepository navRepository;

//    public Nav save(Long id, String sort, String title, String link, NavCategory navCategory){
//        return navRepository.save(
//            Nav.builder().id(id).sort(sort).title(title).link(link).navCategory(navCategory).build()
//        );
//    }
    public List<Nav> saveAll(List<Nav> nav){
        return navRepository.saveAll(nav);
    }
}
