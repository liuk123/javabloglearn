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
    private String title;
    private String link;
    private String category;
    private String descItem;
    private Long RssId;
}
