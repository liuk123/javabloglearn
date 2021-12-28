package com.lk.fishblog.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lk.fishblog.model.NavCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
public class NewNavRequest {
    public Long id;
    private Long sort;
    private String title;
    private String link;
    private Long navCategoryId;
}
