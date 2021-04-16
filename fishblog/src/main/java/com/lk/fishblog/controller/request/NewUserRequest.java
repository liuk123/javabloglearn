package com.lk.fishblog.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lk.fishblog.model.User;
import com.lk.fishblog.security.MyPasswordEncoder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class NewUserRequest {
    private Long id;
    private String username;
    private String password;
    @NotEmpty
    private String phone;

    public User toUser(MyPasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), phone);
    }
}
