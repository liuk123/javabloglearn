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

    private String parentId;
    private String name;
    private String url;
    private String description;

    @JsonIgnore
    @ManyToMany(cascade={}, fetch= FetchType.LAZY)
    @JoinTable(
            name = "b_authority_role",
            joinColumns = {
                    @JoinColumn(name = "authority_id",referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id",referencedColumnName = "id") })
    private List<Role> roleList;
}
