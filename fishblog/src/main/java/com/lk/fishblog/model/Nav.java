package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "B_Nav")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Nav implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private Long sort;
    private String title;
    private String link;

    @JsonIgnore
    @ManyToOne(cascade = {},fetch = FetchType.LAZY)
    @JoinColumn(name="navcategory_id")
    private NavCategory navCategory;

}
