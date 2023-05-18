package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "B_News")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class News implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;

    private String title;
    private String link;
    private String descItem;

    @JsonIgnore
    @ManyToOne(cascade = {},fetch = FetchType.LAZY)
    @JoinColumn(name="rss_id")
    private Rss rss;
}
