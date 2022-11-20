package com.lk.fishblog.controller.request;

import com.lk.fishblog.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class NewArticleRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String descItem;
    private Long tagId;
    private Long tagColumnId;
    private Long category;

    private Long id;
    private String postImage;
}
