package com.lk.fishblog.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "B_COLLECT")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Collect.CollectEmbeddable.class)
public class Collect implements Serializable{
    @Id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private  User user;

    @Id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="article_id")
    private  Article article;

    @Data
    public static class CollectEmbeddable implements  Serializable {
        private User user;
        private Article article;
    }
}
