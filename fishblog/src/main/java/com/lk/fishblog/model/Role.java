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
    @ManyToMany(mappedBy = "roleList", cascade = {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    private List<Article> userList;

    @JsonIgnore
    @ManyToMany(mappedBy = "roleList", cascade = {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    private List<Article> menuList;
}
