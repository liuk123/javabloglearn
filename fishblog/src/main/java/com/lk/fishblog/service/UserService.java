package com.lk.fishblog.service;

import com.lk.fishblog.common.utils.CookieUtil;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(String username, String password, String phone){
        return userRepository.save(
            User
                .builder()
                .username(username)
                .password(password)
                .phone(phone)
                .build()
        );
    }
    public User findById(Long id){
        return userRepository.getOne(id);
    }
    public ResultSet deleteById(Long id, HttpServletRequest request){
        userRepository.deleteById(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }

    public ResultSet login(HttpServletResponse response, String phone, String password){
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "手机号或密码不可为空");
        }
        User user = userRepository.findFirstByPhone(phone);
        if(user == null){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "手机号或密码输入有误");
        }
        String pwd = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(password, pwd)){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "手机号或密码输入有误");
        }

        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "登录成功", user);
    }

    public ResultSet register(String username, String password,String phone){
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(username)) {
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "手机号、密码、用户名不可为空");
        }
        User oUser = userRepository.findFirstByUsername(username);
        if(oUser != null){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "用户名重复");
        }

        User user = save(username,password,phone);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "注册成功", user);
    }
}
