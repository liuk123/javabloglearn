package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewBookmarkRequest {
    public Long id;
    private String title;
    private String link;
    private String icon;
    private String type;
    private Long categoryId;
}
