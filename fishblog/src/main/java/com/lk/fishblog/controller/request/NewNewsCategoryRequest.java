package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewNewsCategoryRequest {
    private Long id;
    private Long sort;
    private String title;
}
