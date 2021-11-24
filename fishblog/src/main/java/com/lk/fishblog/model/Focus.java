package com.lk.fishblog.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "B_FOCUS")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Focus.FocusEmbeddable.class)
public class Focus implements Serializable{
    @Id
    @ManyToOne(cascade = {})
    @JoinColumn(name="user_id")
    private  User user;

    @Id
    @ManyToOne(cascade = {})
    @JoinColumn(name="focus_id")
    private  User focusUser;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode()
    public static class FocusEmbeddable implements  Serializable {
        private User user;
        private User focusUser;
        public FocusEmbeddable(Long userId, Long focusUserId){
            this.user = new User(userId);
            this.focusUser = new User(focusUserId);
        }
        public void setUser(Long userId){
            this.user = new User(userId);
        }
        public void setFocusUser(Long focusUserId){
            this.focusUser = new User(focusUserId);
        }
        public User getUser(){
            return this.user;
        }
        public User getFocusUser(){
            return this.focusUser;
        }
    }
}
