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
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;
    @OneToOne(fetch = FetchType.LAZY)
    private User fromUserId;
    @OneToOne(fetch = FetchType.LAZY)
    private User toUserId;
    private String content;
}
