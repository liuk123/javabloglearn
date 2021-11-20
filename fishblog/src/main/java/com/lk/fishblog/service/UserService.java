package com.lk.fishblog.service;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.User;
import com.lk.fishblog.model.UserGroup;
import com.lk.fishblog.repository.UserRepository;
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
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(Long id){
        return userRepository.getOne(id);
    }
    public ResultSet delById(Long id){
        userRepository.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
    public Page<User> findAll(int pageNum, int pageSize){
        return userRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    public ResultSet register(User u){
        User oUser = userRepository.findFirstByUsername(u.getUsername());
        if(oUser != null){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "用户名重复");
        }
        u.setAccountNonExpired(true);
        u.setAccountNonLocked(true);
        u.setCredentialsNonExpired(true);
        u.setEnabled(true);
        User user = userRepository.save(u);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "注册成功", user);
    }

    public User save(Long id, String pwd, String username, String phone, List<Role> roleList,List<UserGroup> userGroups, Boolean accountNonExpired,Boolean accountNonLocked,Boolean credentialsNonExpired,Boolean enabled){
        return userRepository.save(
            User
                .builder()
                .id(id)
                .roleList(roleList)
                .userGroupList(userGroups)

                .password(pwd)
                .username(username)
                .phone(phone)

                .accountNonExpired(accountNonExpired)
                .accountNonLocked(accountNonLocked)
                .credentialsNonExpired(credentialsNonExpired)
                .enabled(enabled)
                .build()
        );
    }

    /**
     * 关注某人
     * @param userList
     * @param id
     * @return
     */
    public User saveFocus(Long id, List<User> userList){
        return userRepository.save(
                User.builder().id(id).focusList(userList).build()
        );
    }

//    public User saveCollect(Long id, List<Article> collectList){
//        return userRepository.save(
//            User.builder().id(id).collectList(collectList).build()
//        );
//    }
}
