package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "B_USER")
@Builder
@ToString(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;
    @UpdateTimestamp
    public Date updateTime;

    private String username;
    private String phone;
    private String password;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @OrderBy("createTime DESC")
    private List<Article> articleList;

    @ManyToMany(targetEntity = Role.class, fetch=FetchType.EAGER)
    @JoinTable(
            name = "b_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id") })
    private List<Role> roleList;

//    @ManyToMany(targetEntity = UserGroup.class, mappedBy = "userList", fetch=FetchType.LAZY)
    @ManyToMany(targetEntity = UserGroup.class, fetch=FetchType.LAZY)
    @JoinTable(
            name = "b_user_userGroup",
            joinColumns = {
                    @JoinColumn(name = "user_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "userGroup_id") })
    private List<UserGroup> userGroupList;

    public User(Long id){
        this.id = id;
    }
    public User(String username,String password, String phone){
        this.username=username;
        this.password=password;
        this.phone=phone;
    }
    public User(Long id, String username){
        this.id = id;
        this.username = username;
    }
    @JsonIgnore
    public List<Role> getAllRoles(){
        List<Role> roles = new ArrayList<>();
        List<UserGroup> ugs = getUserGroupList();
        if(null != ugs){
            for(UserGroup userGroup: ugs){
                roles.addAll(userGroup.getRoleList());
            }
        }
        roles.addAll(getRoleList());
        return roles;
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> rs = getAllRoles();
        if(null != rs){
            for (Role role : rs) {
                for(Authority authority:role.getAuthorityList())
                    authorities.add(new SimpleGrantedAuthority(authority.getName()));
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
