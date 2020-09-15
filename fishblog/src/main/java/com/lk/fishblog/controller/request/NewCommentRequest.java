package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NewCommentRequest {
    @NotNull
    private Long fromUserId;
    @NotNull
    private String fromUserName;
    @NotNull
    private Long articleId;
    @NotEmpty
    private String content;
}
