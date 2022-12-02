package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_TagColumn")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "TagColumnEntity",
                attributeNodes = {
                        @NamedAttributeNode(value="tagList"),
                }
        )
})
public class TagColumn extends BaseEntity implements Serializable{
    private String title;
    private Long sort;

    @JsonIgnore
    @OneToMany(cascade = { }, mappedBy = "tagColumn", fetch=FetchType.LAZY)
    private  List<Article> articleList;

    public TagColumn(Long id){
        this.id = id;
    }

    @OneToMany(cascade = { }, mappedBy = "tagColumn", fetch=FetchType.EAGER)
    private  List<Tag> tagList;

    @Override
    public String toString() {
        return "TagColumn{" +
                "id='" + getId() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
    }

}
