package com.lk.fishblog.service;

import com.lk.fishblog.model.Nav;

import com.lk.fishblog.model.NavCategory;
import com.lk.fishblog.repository.NavRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class NavService {
    @Autowired
    NavRepository navRepository;

//    public void saveAll(List<Nav> nav){
//        navRepository.saveAll(nav);
//    }
    public Nav save(Long id, Long sort, String title, String link, NavCategory navCategory){
        return navRepository.save(
                Nav.builder().id(id).sort(sort).title(title).link(link).navCategory(navCategory).build()
        );
    }
//    public List<Nav> findNav(Long id){
//        return navRepository.findByNavCategory_Id(id);
//    }
    public List<Nav> findNavByCIds(List<Long> cIds){
        return navRepository.findByNavCategory_IdIn(cIds);
    }
    @Transactional
    public void delOne(Long id){
        navRepository.deleteById(id);
    }
    @Transactional
    public void del(List<Long> ids){
        List<Nav> navs = new ArrayList<>();
        for(Long id: ids){
            navs.add(new Nav(id));
        }
        navRepository.deleteAll(navs);
    }
}
