package com.lk.fishblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "B_Friend")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Friend implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;

    private String title;
    private String link;
    private String icon;
    private Long sort;
    private String category;
    private String descItem;
}
