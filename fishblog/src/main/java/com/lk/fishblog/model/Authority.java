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
@ToString(callSuper = true)
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

    private Long parentId;
    private String name;
    private String url;
    private String description;

    @JsonIgnore
    @ManyToMany(targetEntity = Role.class, mappedBy = "authorityList")
    private List<Role> roleList;

    public Authority(Long id, Long parentId, String name, String url, String description){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.description = description;
    }
    public Authority(Long id){
        this.id = id;
    }
}
