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
    private String phone;

    @JsonIgnoreProperties(value = {"author"})
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
