package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "B_USER")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable{
    private String username;
    private String phone;
    private String password;
    private Integer role;

    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @OrderBy("createTime DESC")
    private List<Article> articleList;


    public User(Long id){
        this.id = id;
    }
    public User(Long id, String username){
        this.id = id;
        this.username = username;
    }
    public User(Long id, String username, String phone){
        this.id = id;
        this.username = username;
        this.phone = phone;
    }
    public User(Long id, String username, String phone, String password, Integer role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }
    public List<Article> getArticleList(){
        List<Article> articles = new ArrayList<>();
        if(null != this.articleList){
            for(Article val: this.articleList){
                articles.add(new Article(val.getId(), val.getTitle(),val.getDescItem(),val.getTagList()));
            }
        }
        return articles;
    }
    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
