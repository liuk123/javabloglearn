package com.lk.fishblog.controller.request;

import com.lk.fishblog.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NewRoleRequest {
    private Long id;
    private String name;
    private String description;
    private List<Long> authorityIds;

    public Role toRole(){
        return new Role(id,name,description);
    }
}
