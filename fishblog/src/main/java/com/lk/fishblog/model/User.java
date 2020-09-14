package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_USER")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class User extends BaseEntity implements Serializable{
    private String username;
    private String password;
    private Integer role;
    private Long phone;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "b_user_article",
//        joinColumns = {
//            @JoinColumn(name = "user_id") },
//        inverseJoinColumns = {
//            @JoinColumn(name = "article_id") })
//    private List<Article> articleList;
    @JsonIgnoreProperties(value = {"author"})
    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Article> articleList;

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
