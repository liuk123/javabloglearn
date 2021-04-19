package com.lk.fishblog.security;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.User;
import com.lk.fishblog.model.UserGroup;
import com.lk.fishblog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 登录专用类
 * 自定义类，实现了UserDetailsService接口，用户登录时调用的第一类
 *
 */
@Service
public class MyCustomUserService implements UserDetailsService {

    final UserRepository userRepository;


    public MyCustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在这里可以自己调用数据库，对username进行查询，看看在数据库中是否存在
        User u = userRepository.findFirstByUsername(username);
        if(null == u){
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        Set<Authority> auth = new HashSet<>();
        Set<UserGroup> userGroupList = u.getUserGroupList();

        u.getAuthorities();

        for(UserGroup group : userGroupList){
            List<Role> roles = group.getRoleList();
            for(Role role: roles){
                List<Authority> authorityList = role.getAuthorityList();
                for(Authority authority : authorityList){
//                    u.set
                }
            }

        }
        return u;
    }
}