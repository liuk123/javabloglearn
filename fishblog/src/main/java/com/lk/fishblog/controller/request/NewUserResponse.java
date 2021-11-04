package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NewUserResponse {
    private Long id;
    private String username;
    private String phone;

    public NewUserResponse(Long id, String username){
        this.id = id;
        this.username = username;
    }
}
