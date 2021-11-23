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
@Table(name = "B_AUTHORITY")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;
    @UpdateTimestamp
    public Date updateTime;

    private Long pid;
    private String name;
    private String url;
    private String description;

    @JsonIgnore
    @ManyToMany(targetEntity = Role.class, mappedBy = "authorityList", fetch=FetchType.LAZY)
    private List<Role> roleList;

    @JsonIgnore
    @ManyToMany(targetEntity = Menu.class, mappedBy = "authorityList", fetch=FetchType.LAZY)
    private List<Menu> menuList;

    public Authority(Long id, Long pid, String name, String url, String description){
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.description = description;
    }
    public Authority(Long id){
        this.id = id;
    }
    public Authority(String name){
        this.name = name;
    }
}
