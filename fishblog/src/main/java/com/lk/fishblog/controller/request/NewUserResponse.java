package com.lk.fishblog.controller.request;

import com.lk.fishblog.model.Authority;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class NewUserResponse {
    private Long id;
    private String username;
    private String avatar;
    private String phone;
    private String email;
    private Date createTime;
    private List<Authority> authorities;

    public NewUserResponse(Long id, String username, String avatar, String email, Date createTime, List<Authority> authorities){
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.createTime = createTime;
        this.authorities = authorities;
    }
}
