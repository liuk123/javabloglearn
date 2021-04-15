package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @ManyToMany(mappedBy = "roleList",fetch = FetchType.EAGER)
    private List<Authority> Authoritys;
    @Override
    public String toString() {
        return "Role{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
