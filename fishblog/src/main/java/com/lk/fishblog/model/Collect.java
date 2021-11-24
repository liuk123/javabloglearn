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
    @ManyToOne(cascade = {}, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private  User user;

    @Id
    @ManyToOne(cascade = {})
    @JoinColumn(name="article_id")
    private  Article article;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode()
    public static class CollectEmbeddable implements  Serializable {
        private User user;
        private Article article;

        public CollectEmbeddable(Long userId, Long articleId){
            this.user = new User(userId);
            this.article = new Article(articleId);
        }
        public User getUser(){
            return this.user;
        }
        public void setUser(Long userId){
            this.user = new User(userId);
        }
        public Article getArticle(){
            return this.article;
        }
        public void setArticle(Long articleId){
            this.article = new Article(articleId);
        }
    }
}
