package com.lk.fishblog.controller.request;

import com.lk.fishblog.model.User;
import com.lk.fishblog.security.MyPasswordEncoder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NewUserRequest {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String avatar;
    private List<Long> roleIds;
    private List<Long> userGroupIds;

    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    public User toUser(MyPasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), avatar, email);
    }
}
