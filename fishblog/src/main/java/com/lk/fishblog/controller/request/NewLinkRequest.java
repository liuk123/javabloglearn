package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewLinkRequest {
    public Long id;
    private String title;
    private String link;
    private String icon;
    private Long sort;
    private String category;
    private String descItem;
}
