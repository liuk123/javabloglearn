package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

    @JsonIgnoreProperties(value = { "articleList", "password" })
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="user_id")
    private User author;

    @JsonIgnoreProperties(value = {"article"})
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
