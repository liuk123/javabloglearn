package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "B_USER")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {
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
                    @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private List<Role> roleList;

    @ManyToMany(targetEntity = UserGroup.class, mappedBy = "userList", fetch=FetchType.LAZY)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(null != getRoleList()){
            for (Role role : getRoleList()) {
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
