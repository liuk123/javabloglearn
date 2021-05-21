package com.lk.fishblog.service;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.UserGroup;
import com.lk.fishblog.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Page<Role> findRoles(int pageNum, int pageSize){
        return roleRepository.findAll(PageRequest.of(pageNum, pageSize));
    }
    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public Role save(Long id, String name, String description, List<Authority> authorityList){
        return roleRepository.save(
            Role
                .builder()
                .id(id)
                .name(name)
                .description(description)
                .authorityList(authorityList)
                .build()
        );
    }
    public void delById(Long id){
        roleRepository.deleteById(id);
    }
}