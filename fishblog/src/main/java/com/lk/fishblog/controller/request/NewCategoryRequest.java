package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class NewCategoryRequest {
    private  Long id;
    @NotEmpty
    private String name;
    private Long articleId;

}
