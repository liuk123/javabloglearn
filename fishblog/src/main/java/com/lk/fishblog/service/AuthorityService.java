package com.lk.fishblog.service;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.AuthorityRepository;
import com.lk.fishblog.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public Page<Authority> findAuth(int pageNum, int pageSize){
        return authorityRepository.findAll(PageRequest.of(pageNum, pageSize));
    }
    public List<Authority> findAllAuth(){
        return authorityRepository.findAll();
    }

    public Authority save(Authority auth){
        return authorityRepository.save(auth);
    }
    public void delById(Long id){
        authorityRepository.deleteById(id);
    }

}