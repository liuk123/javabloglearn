package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "B_ROLE")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable{
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

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, mappedBy = "roleList", fetch=FetchType.LAZY)
    private List<User> userList;

    @JsonIgnore
    @ManyToMany(targetEntity = UserGroup.class, mappedBy = "roleList", fetch=FetchType.LAZY)
    private List<UserGroup> userGroupList;

    @ManyToMany(cascade={}, fetch= FetchType.EAGER)
    @JoinTable(
            name = "b_role_authority",
            joinColumns = {
                    @JoinColumn(name = "role_id",referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_id",referencedColumnName = "id") })
    private List<Authority> authorityList;

    public Role(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Role(Long id){
        this.id = id;
    }
}
