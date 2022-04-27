package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewBookmarkCategoryRequest {
    private Long id;
    private Long pid;
    private Long sort;
    private String title;
    private String icon;
}
