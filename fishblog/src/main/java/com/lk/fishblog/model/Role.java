package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "B_ROLE")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity implements Serializable{
    private String name;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roleList", cascade = {}, fetch=FetchType.LAZY)
    private List<User> userList;

    @ManyToMany(targetEntity = UserGroup.class, mappedBy = "roleList")
    private Set<UserGroup> userGroups = new HashSet<>();


    @ManyToMany(cascade={}, fetch= FetchType.EAGER)
    @JoinTable(
            name = "b_role_authority",
            joinColumns = {
                    @JoinColumn(name = "role_id",referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_id",referencedColumnName = "id") })
    private List<Authority> AuthorityList;

}
