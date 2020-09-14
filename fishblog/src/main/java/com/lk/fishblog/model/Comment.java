package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_COMMENT")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity implements Serializable {

    @JsonIgnoreProperties(value = { "articleList", "password" })
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @JsonIgnoreProperties(value = {"content"})
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="article_id")
    private Article article;

    private String content;

    @JsonIgnoreProperties(value = {"comment"})
    @OneToMany(mappedBy = "comment",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Reply> replyList;

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + getId() + '\'' +
                ", content='" + content + '\'' +
                ", replyList='" + replyList + '\'' +
                '}';
    }
}
