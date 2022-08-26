package com.lk.fishblog.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_NewsCategory")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "NewsCategoryEntity",
                attributeNodes = {
                        @NamedAttributeNode(value="newsList")
                }
        )
})
public class NewsCategory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private Long sort;
    private String title;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "newsCategory", fetch=FetchType.EAGER)
    private  List<News> newsList;

    public NewsCategory(Long id){
        this.id = id;
    }
}
