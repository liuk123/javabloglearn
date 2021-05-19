package com.lk.fishblog.service;

import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.UserGroup;
import com.lk.fishblog.repository.UserGroupRepository;
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
public class UserGroupService {
    @Autowired
    UserGroupRepository userGroupRepository;

    public Page<UserGroup> findAll(int pageNum, int pageSize){
        return userGroupRepository.findAll(PageRequest.of(pageNum, pageSize));
    }
    public UserGroup save(Long id, String name, String description, List<Role> roleList){
        return userGroupRepository.save(
                UserGroup
                    .builder()
                    .id(id)
                    .name(name)
                    .description(description)
                    .roleList(roleList)
                    .build()
        );
    }
    public void delById(Long id){
        userGroupRepository.deleteById(id);
    }
}