package com.lk.fishblog.security;

import com.lk.fishblog.model.User;
import com.lk.fishblog.model.UserGroup;
import com.lk.fishblog.repository.UserGroupRepository;
import com.lk.fishblog.repository.UserRepository;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录专用类
 * 自定义类，实现了UserDetailsService接口，用户登录时调用的第一类
 *
 */
@Service
public class MyCustomUserService implements UserDetailsService {

    final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;


    public MyCustomUserService(UserRepository userRepository, UserGroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;
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
            throw new UsernameNotFoundException("用户名/密码错误");
        }else if (!u.isEnabled()) {
            throw new DisabledException("用户被禁用");
        } else if (!u.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if (!u.isAccountNonLocked()) {
            throw new LockedException("账号被锁定");
        } else if (!u.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }
        List<UserGroup> ug= userGroupRepository.findUserGroupsByUserList(u);
        u.setUserGroupList(ug);
        return u;
    }
}