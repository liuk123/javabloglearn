package com.lk.fishblog.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NewReplyRequest {
    @NotNull
    private Long commentId;
    @NotNull
    private Long toUserId;
    @NotEmpty
    private String content;
}