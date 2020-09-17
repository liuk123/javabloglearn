package com.lk.fishblog.service;

import com.lk.fishblog.common.utils.CookieUtil;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CookieUtil cookieUtil;

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
    public User findFirstByUsername(String username){ return userRepository.findFirstByUsername(username);}
    public User findByUsernameOrPhone(String username, Long phone){ return  userRepository.findFirstByUsernameOrPhone(username, phone); }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public ResultSet login(HttpServletResponse response, String username, String password){
        User user = findFirstByUsername(username);
        if(user == null){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "用户名或密码输入有误");
        }
        String pwd = user.getPassword();
        if(!password.equals(pwd)){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "用户名或密码输入有误");
        }
        String token = UUID.randomUUID().toString().replace("-","");
        cookieUtil.addCookie(response,token, user);

        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "登录成功", token);
    }
}
