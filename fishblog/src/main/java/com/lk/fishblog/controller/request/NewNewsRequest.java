package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NewNewsRequest {
    public Long id;
    private Long sort;
    private String title;
    private String link;
    private Long categoryId;
    private String type;
    private String descItem;
}
