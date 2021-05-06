package com.lk.fishblog.service;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Menu;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.repository.MenuRepository;
import com.lk.fishblog.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Page<Role> findAll(int pageNum, int pageSize){
        return roleRepository.findAll(PageRequest.of(pageNum, pageSize));
    }
    public void save(Role r){
        roleRepository.save(r);
    }
    public void delById(Long id){
        roleRepository.deleteById(id);
    }
}