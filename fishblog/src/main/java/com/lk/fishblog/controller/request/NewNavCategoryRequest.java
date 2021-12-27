package com.lk.fishblog.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lk.fishblog.model.Nav;
import com.lk.fishblog.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
public class NewNavCategoryRequest {
    private Long id;
    private String pid;
    private String sort;
    private String title;
}
