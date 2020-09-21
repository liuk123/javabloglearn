package com.lk.fishblog.service;

import com.lk.fishblog.common.utils.CookieUtil;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CookieUtil cookieUtil;

    public User save(String username, String password, String phone, Integer role){
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
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public ResultSet login(HttpServletResponse response, String phone, String password){
        User user = userRepository.findFirstByPhone(phone);
        if(user == null){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "手机号或密码输入有误");
        }
        String pwd = user.getPassword();
        if(!password.equals(pwd)){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "手机号或密码输入有误");
        }
        String token = UUID.randomUUID().toString().replace("-","");
        cookieUtil.addCookie(response,token, user);

        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "登录成功", user);
    }

    public ResultSet register(HttpServletResponse response, String username, String password,String phone, Integer role){
        User user = save(username,password,phone,role);
        String token = UUID.randomUUID().toString().replace("-","");
        cookieUtil.addCookie(response,token, user);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "注册成功", user);
    }

    public ResultSet getCurrentUserBySession(HttpServletRequest request){
        User user =cookieUtil.getLoginUser(request);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "获取用户信息", user);
    }
}
