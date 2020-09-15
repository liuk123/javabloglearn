package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(String username, String password, Long phone, Integer role){
        return userRepository.save(
            User
                .builder()
                .username(username)
                .password(password)
                .phone(phone)
                .role(role)
                .build()
        );
    }
    public User findById(Long id){
        return userRepository.getOne(id);
    }
    public User findByUsernameOrPhone(String username, Long phone){ return  userRepository.findFirstByUsernameOrPhone(username, phone); }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
