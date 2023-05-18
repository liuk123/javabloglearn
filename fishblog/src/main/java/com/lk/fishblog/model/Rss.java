package com.lk.fishblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "B_Rss")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "RssEntity",
                attributeNodes = {
                        @NamedAttributeNode(value="newsList")
                }
        )
})
public class Rss implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(updatable = false)
    @CreationTimestamp
    public Date createTime;

    private String title;
    private String link;
    private Long sort;
    private String category;
    private String descItem;

    @OneToMany(cascade = { CascadeType.REMOVE,CascadeType.REFRESH }, mappedBy = "rss", fetch=FetchType.EAGER)
    private List<News> newsList;

    public Rss(Long id){
        this.id = id;
    }
}
