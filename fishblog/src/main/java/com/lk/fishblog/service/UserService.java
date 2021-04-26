package com.lk.fishblog.service;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(Long id){
        return userRepository.getOne(id);
    }
    public ResultSet deleteById(Long id){
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
        User user = userRepository.save(u);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "注册成功", user);
    }
}
