package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class NewUserRequest {

    private String username;
    private String password;
    @NotEmpty
    private Long phone;
}
