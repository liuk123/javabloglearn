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

    @JsonIgnoreProperties(value = { "articleList", "password" })
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="user_id")
    private User author;

    @JsonIgnoreProperties(value = {"article"})
    @OneToMany(mappedBy = "article",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Comment> commentList;

    @Override
    public String toString() {
        return "Article{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
