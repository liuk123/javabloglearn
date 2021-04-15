package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
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
    private String enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @OrderBy("createTime DESC")
    private List<Article> articleList;

    @ManyToMany(cascade={}, fetch=FetchType.EAGER)
    @JoinTable(
            name = "b_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private List<Role> roleList;


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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = this.getRoleList();
        for (Role role : roles) {
            for(Authority aurh:role.getAuthoritys())
                auths.add(new SimpleGrantedAuthority(aurh.getName()));
        }
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
