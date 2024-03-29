package com.lk.fishblog.controller.request;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
@ToString
public class NewMenuRequest {
    private Long id;
    private Long pid;
    private Long sort;
    private String title;
    private String type;
    private String icon;
    private String disabled;
    private String selected;
    private String open;
    private String route;
    private String link;
    private Boolean isMenuShow;
    private Boolean isBreadcrumbShow;
    private String meta;
    private  List<Long> authorityIds;
}
