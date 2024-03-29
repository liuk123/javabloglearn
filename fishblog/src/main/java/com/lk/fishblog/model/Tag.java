package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_TAG")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity implements Serializable{
    private String title;
    private Long sort;
    private Long pid;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH }, mappedBy = "tag", fetch=FetchType.LAZY)
    private  List<Article> articleList;

    @JsonIgnore
    @ManyToOne(cascade = {}, fetch=FetchType.LAZY)
    @JoinColumn(name="tag_column_id")
    private TagColumn tagColumn;


    public Tag(Long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + getId() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
    }

}
