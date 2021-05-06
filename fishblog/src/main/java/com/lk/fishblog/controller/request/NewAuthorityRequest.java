package com.lk.fishblog.controller.request;

import com.lk.fishblog.model.Authority;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewAuthorityRequest {
    private Long id;
    private Long parentId;
    private String name;
    private String url;
    private String description;

    public Authority toAuthority(){
        return new Authority(id,parentId,name,url,description);
    }
}
