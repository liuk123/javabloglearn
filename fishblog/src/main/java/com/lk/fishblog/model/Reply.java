package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "B_REPLY")
@Builder
//@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude="comment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends BaseEntity implements Serializable{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="comment_id")
    private Comment comment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="from_user_id")
    private User fromUser;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="to_user_id")
    private User toUser;

    private String content;

}
