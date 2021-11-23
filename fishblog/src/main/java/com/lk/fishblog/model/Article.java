package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String postImage;

    @ManyToOne(cascade = {}, optional=false)
    @JoinColumn(name="user_id")
    private User author;

    @OneToMany(mappedBy = "article",cascade={CascadeType.REMOVE,CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @OrderBy("createTime DESC")
    private List<Comment> commentList;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "b_article_tag",
            joinColumns = {
                    @JoinColumn(name = "article_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id") })
    private  List<Tag> tagList;

    @ManyToOne(cascade = {}, optional=false, fetch=FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "article", fetch=FetchType.LAZY)
    private  List<Collect> collectList;


    public  Article(Long id){
        this.id = id;
    }
}
