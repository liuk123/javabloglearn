package com.lk.fishblog.service;

import com.lk.fishblog.model.Reply;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.ReplyRepository;
import com.lk.fishblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User insertOneUser(User u){
        return userRepository.save(u);
    }
}
