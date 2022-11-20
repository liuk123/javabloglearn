package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class NewTagRequest {
    @NotEmpty
    private String title;
    private Long pid;
    private Long sort;
    private Long tagColumnId;
}
