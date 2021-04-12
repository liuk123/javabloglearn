package com.lk.fishblog.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_MENU")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity implements Serializable{
    private String title;
    private String type;
    private String icon;
    private String disabled;
    private String selected;
    private String open;
    private String route;
    private String link;
    private String permission;

    @ManyToMany(cascade={}, fetch= FetchType.EAGER)
    @JoinTable(
            name = "b_menu_role",
            joinColumns = {
                    @JoinColumn(name = "menu_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id") })
    private List<Tag> roleList;
}
