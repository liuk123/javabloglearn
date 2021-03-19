package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "tagList", cascade = {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    private List<Article> articleList;

    public Tag(Long id){
        this.id = id;
    }


    @Override
    public String toString() {
        return "Tag{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
