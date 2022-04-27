package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "B_Bookmark")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String title;
    private String link;
    private String icon;

    @JsonIgnore
    @ManyToOne(cascade = {},fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private BookmarkCategory bookmarkCategory;

}
