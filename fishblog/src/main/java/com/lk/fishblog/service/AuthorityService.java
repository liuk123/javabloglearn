package com.lk.fishblog.service;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.AuthorityRepository;
import com.lk.fishblog.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@Service
@Transactional
@Slf4j
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public Page<Authority> findAll(int pageNum, int pageSize){
        return authorityRepository.findAll(PageRequest.of(pageNum, pageSize));
    }
    public void save(Authority auth){
        authorityRepository.save(auth);
    }
    public void delById(Long id){
        authorityRepository.deleteById(id);
    }

}