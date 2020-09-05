package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_ARTICLE")
@Builder
//@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity implements Serializable{

    private String title;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String content;

    @JsonIgnoreProperties(value = { "articleList" })
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "b_article_comment", joinColumns = {
            @JoinColumn(name = "article_id") }, inverseJoinColumns = { @JoinColumn(name = "comment_id") })
    private List<Comment> commentList;

}
