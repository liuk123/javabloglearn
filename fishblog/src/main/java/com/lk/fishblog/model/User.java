package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_USER")
@Builder
//@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "articleList")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable{
    private String username;
    private String password;
    private Integer role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "b_user_article",
        joinColumns = {
            @JoinColumn(name = "user_id") },
        inverseJoinColumns = {
            @JoinColumn(name = "article_id") })
    private List<Article> articleList;

}
