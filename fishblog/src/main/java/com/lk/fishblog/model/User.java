package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @OrderBy("createTime DESC")
    private List<Article> articleList;

    @ManyToMany(cascade={}, fetch=FetchType.EAGER)
    @JoinTable(
            name = "b_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id") })
    private List<Tag> roleList;


    public User(Long id){
        this.id = id;
    }
    public User(String username){
        this.username=username;
    }
    public User(Long id, String username){
        this.id = id;
        this.username = username;
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
