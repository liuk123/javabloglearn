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
@Table(name = "B_CATEGORY")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;
    @UpdateTimestamp
    public Date updateTime;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category",cascade={}, fetch=FetchType.LAZY)
    private List<Article> articleList;

    @JsonIgnore
    @ManyToOne(cascade = {}, optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User author;

    @Override
    public String toString() {
        return "Category{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }
}
