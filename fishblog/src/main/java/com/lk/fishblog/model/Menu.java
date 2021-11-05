package com.lk.fishblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "B_MENU")
@ToString()
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;
    @UpdateTimestamp
    public Date updateTime;

    private String pid;
    private String sort;
    private String title;
    private String type;
    private String icon;
    private String disabled;
    private String selected;
    private String open;
    private String route;
    private String link;
    private Boolean isMenuShow;
    private Boolean isBreadcrumbShow;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "b_menu_authority",
            joinColumns = {
                    @JoinColumn(name = "menu_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_id") })
    private  List<Authority> authorityList;
}
