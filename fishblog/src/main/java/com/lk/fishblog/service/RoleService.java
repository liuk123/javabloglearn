package com.lk.fishblog.service;

import com.lk.fishblog.model.Menu;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.repository.MenuRepository;
import com.lk.fishblog.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

//    public List<Role> getRoleById(Long id){
//        return roleRepository.findRoleById(id);
//    }
}