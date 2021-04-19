package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_USERGROUP")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup extends BaseEntity implements Serializable{
    private String name;
    private String description;

    @JsonIgnore
    @ManyToMany(cascade={}, fetch=FetchType.LAZY)
    @JoinTable(
            name = "b_userGroup_user",
            joinColumns = {
                    @JoinColumn(name = "userGroup_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id") })
    private List<User> userList;

    @ManyToMany(cascade={}, fetch=FetchType.EAGER)
    @JoinTable(
            name = "b_userGroup_role",
            joinColumns = {
                    @JoinColumn(name = "userGroup_id", referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private List<Role> roleList;
}
