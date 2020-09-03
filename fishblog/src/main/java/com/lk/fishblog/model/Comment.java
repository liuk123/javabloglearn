package com.lk.fishblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "B_COMMENT")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity implements Serializable {

    @OneToOne(fetch = FetchType.LAZY)
    private User fromUser;
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
    private String content;

    @OneToMany(fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private List<Reply> replyList;
}
