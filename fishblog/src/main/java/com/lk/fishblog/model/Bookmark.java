package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "B_Bookmark")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;
    @UpdateTimestamp
    public Date updateTime;

    private String title;
    private String link;
    private String icon;
    private String type;

    @JsonIgnore
    @ManyToOne(cascade = {},fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private BookmarkCategory bookmarkCategory;

}
