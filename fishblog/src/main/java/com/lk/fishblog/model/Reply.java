package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "B_REPLY")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends BaseEntity implements Serializable{

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="comment_id")
    private Comment comment;

    @JsonIgnoreProperties(value = { "articleList", "password" })
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @JsonIgnoreProperties(value = { "articleList", "password" })
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="to_user_id")
    private User toUser;

    private String content;

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + getId() + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
