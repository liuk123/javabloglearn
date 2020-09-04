package com.lk.fishblog.service;

import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User u){
        return userRepository.save(u);
    }
    public User findById(Long id){
        return userRepository.findById(id).get();
    }
}
