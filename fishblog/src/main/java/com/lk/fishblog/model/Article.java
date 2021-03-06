package com.lk.fishblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "B_ARTICLE")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;
    @UpdateTimestamp
    public Date updateTime;

    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;
    private String descItem;

    @ManyToOne(cascade = {}, optional=false)
    @JoinColumn(name="user_id")
    private User author;

    @OneToMany(mappedBy = "article",cascade={CascadeType.REMOVE,CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @OrderBy("createTime DESC")
    private List<Comment> commentList;

    @ManyToMany(cascade={}, fetch=FetchType.LAZY)
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
    public  Article(String content){
        this.content = content;
    }
    public  Article(User author ){
        this.author = author;
    }
}
