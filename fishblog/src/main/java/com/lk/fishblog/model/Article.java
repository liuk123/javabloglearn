package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "B_ARTICLE")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity implements Serializable{

    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;
    private String descItem;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="user_id")
    private User author;

    @OneToMany(mappedBy = "article",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @OrderBy("createTime DESC")
    private List<Comment> commentList;

    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE}, fetch=FetchType.EAGER)
    @JoinTable(
            name = "b_article_tag",
            joinColumns = {
                    @JoinColumn(name = "article_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id") })
    private  List<Tag> tagList;


    public  Article(Long id){
        this.id = id;
    }

    public Article(long id, String title, String descItem){
        this.id=id;
        this.title=title;
        this.descItem = descItem;
    }
    public Article(long id, String title, String descItem, List<Tag> tagList){
        this.id=id;
        this.title=title;
        this.descItem = descItem;
        this.tagList = tagList;
    }
    public User getAuthor() {
        if(null != author){
            return new User(author.getId(),author.getUsername(),author.getPhone());
        }else{
            return new User();
        }

    }
    public List<Comment> getCommentList(){
        List<Comment> comments = new ArrayList<>();
        if(null!=this.commentList){
            for(Comment val: this.commentList){
                comments.add(new Comment(val.getId(), val.getFromUser(), val.getContent(), val.getReplyList()));
            }
        }
        return comments;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + getId() + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
