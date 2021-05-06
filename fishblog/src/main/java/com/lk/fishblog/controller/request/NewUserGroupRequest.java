package com.lk.fishblog.controller.request;

import com.lk.fishblog.model.UserGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewUserGroupRequest {
    private Long id;
    private String name;
    private String description;

    public UserGroup toUserGroup(){
        return new UserGroup(id,name,description);
    }
}
