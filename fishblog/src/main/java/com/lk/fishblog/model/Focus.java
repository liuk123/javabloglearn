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
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private  User user;

    @Id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="focus_id")
    private  User focus;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode()
    public static class FocusEmbeddable implements  Serializable {
        private User user;
        private User focus;
    }
}
