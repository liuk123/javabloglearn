package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_COMMENT")
@Builder
//@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude="article")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity implements Serializable {

    @JsonIgnoreProperties(value = { "articleList", "password" })
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="article_id")
    private Article article;

    private String content;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "b_comment_reply", joinColumns = {
            @JoinColumn(name = "comment_id") }, inverseJoinColumns = { @JoinColumn(name = "reply_id") })
    private List<Reply> replyList;
}
