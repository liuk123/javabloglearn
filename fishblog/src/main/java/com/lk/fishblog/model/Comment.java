package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="article_id")
    private Article article;

    private String content;

    @OneToMany(mappedBy = "comment",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @OrderBy("createTime ASC")
    private List<Reply> replyList;

    public Comment(Long id){
        this.id = id;
    }
    public Comment(Long id, User fromUser ){
        this.id = id;
        this.fromUser = fromUser;
    }
    public Comment(Long id, User fromUser, String content, List<Reply> replyList ){
        this.id = id;
        this.fromUser = fromUser;
        this.content = content;
        this.replyList = replyList;
    }
    public User getFromUser(){
        if(null!=fromUser){
            return new User(fromUser.getId(),fromUser.getUsername(),fromUser.getPhone());
        }else{
            return new User();
        }

    }
    public Article getArticle(){
        if(null!=article){
            return new Article(article.getId(), article.getTitle(),article.getDescItem());
        }else{
            return new Article();
        }

    }
    public List<Reply> getReplyList(){
        List<Reply> replys = new ArrayList<>();
        if(null!=this.replyList){
            for(Reply val: this.replyList){
                replys.add(new Reply(val.getId(), val.getComment(), val.getFromUser(), val.getToUser(), val.getContent()));
            }
        }
        return replys;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id='" + getId() + '\'' +
                ", content='" + content + '\'' +
                ", replyList='" + replyList + '\'' +
                '}';
    }
}
