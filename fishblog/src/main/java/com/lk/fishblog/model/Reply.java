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

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="to_user_id")
    private User toUser;

    private String content;

    public Reply(Long id, Comment comment, User fromUser, User toUser, String content){
        this.id = id;
        this.comment=comment;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
    }
    public Comment getComment(){
        return new Comment(this.comment.getId(),this.comment.getFromUser());
    }
    public User getFromUser(){
        return new User(this.fromUser.getId(),this.fromUser.getUsername(),this.fromUser.getPhone());
    }
    public User getToUser(){
        return new User(this.toUser.getId(),this.toUser.getUsername(),this.toUser.getPhone());
    }
    @Override
    public String toString() {
        return "Reply{" +
                "id='" + getId() + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
