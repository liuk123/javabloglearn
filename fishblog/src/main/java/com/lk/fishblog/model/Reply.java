package com.lk.fishblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "B_REPLY")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends BaseEntity implements Serializable{
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name="comment_id")
    private Comment comment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="from_user_id")
    private User fromUser;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="to_user_id")
    private User toUser;
    private String content;
}
