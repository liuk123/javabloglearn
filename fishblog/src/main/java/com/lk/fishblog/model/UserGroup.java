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
@Table(name = "B_USERGROUP")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;
    @UpdateTimestamp
    public Date updateTime;

    private String name;
    private String description;

    @ManyToMany(targetEntity = User.class, fetch=FetchType.LAZY)
    @JoinTable(
            name = "b_userGroup_user",
            joinColumns = {
                    @JoinColumn(name = "userGroup_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id") })
    private List<User> userList;

    @ManyToMany(targetEntity = Role.class, fetch=FetchType.EAGER)
    @JoinTable(
            name = "b_userGroup_role",
            joinColumns = {
                    @JoinColumn(name = "userGroup_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id") })
    private List<Role> roleList;

    public UserGroup(Long id, String name, String description){
        this.id=id;
        this.name=name;
        this.description=description;
    }
    public UserGroup(Long id){
        this.id=id;
    }
}
