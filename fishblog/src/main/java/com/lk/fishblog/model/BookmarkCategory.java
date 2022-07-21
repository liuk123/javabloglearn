package com.lk.fishblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "B_BookmarkCategory")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BookmarkCategory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;
    @UpdateTimestamp
    public Date updateTime;

    private Long pid;
    private Long sort;
    private String title;
    private String icon;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "bookmarkCategory", fetch=FetchType.LAZY)
    private  List<Bookmark> bookmarkList;

    public BookmarkCategory(Long id){
        this.id = id;
    }
}
