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

    private String parentId;
    private String title;
    private String type;
    private String icon;
    private String disabled;
    private String selected;
    private String open;
    private String route;
    private String link;
    private String permission;

    @ManyToMany(cascade={}, fetch= FetchType.LAZY)
    @JoinTable(
            name = "b_menu_role",
            joinColumns = {
                    @JoinColumn(name = "menu_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id") })
    private List<Role> roleList;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + getId() + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", disabled='" + disabled + '\'' +
                ", selected='" + selected + '\'' +
                ", open='" + open + '\'' +
                ", route='" + route + '\'' +
                ", link='" + link + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
