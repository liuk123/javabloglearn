package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
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

//    public Reply(Long id, Comment comment, User fromUser, User toUser, String content){
//        this.id = id;
//        this.comment=comment;
//        this.fromUser = fromUser;
//        this.toUser = toUser;
//        this.content = content;
//    }
//    public Comment getComment(){
//        if(null!=comment){
//            return new Comment(comment.getId(), comment.getFromUser());
//        }else {
//            return new Comment();
//        }
//    }
//    public User getFromUser(){
//        if(null!=fromUser){
//            return new User(fromUser.getId(),fromUser.getUsername(),fromUser.getPhone());
//        }else{
//            return new User();
//        }
//
//    }
//    public User getToUser(){
//        if(null!=toUser){
//            return new User(this.toUser.getId(),this.toUser.getUsername(),this.toUser.getPhone());
//        }else{
//            return new User();
//        }
//
//    }
    @Override
    public String toString() {
        return "Reply{" +
                "id='" + getId() + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
