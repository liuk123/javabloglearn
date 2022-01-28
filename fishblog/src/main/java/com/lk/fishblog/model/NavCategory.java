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
@Table(name = "B_NavCategory")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NavCategory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private Long pid;
    private Long sort;
    private String title;

    @JsonIgnore
    @ManyToOne(cascade = {}, optional=false)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "navCategory", fetch=FetchType.EAGER)
    private  List<Nav> navList;

    public NavCategory(Long id){
        this.id = id;
    }
}
