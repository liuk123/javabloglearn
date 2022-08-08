package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "B_Link")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Link implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;

    private String title;
    private String link;
    private String icon;
    private String category;
    private String descItem;
}
