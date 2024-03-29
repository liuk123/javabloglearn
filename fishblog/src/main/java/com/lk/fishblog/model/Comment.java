package com.lk.fishblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "B_COMMENT")
@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "CommentEntity",
                attributeNodes = {
                        @NamedAttributeNode(value="replyList")
                }
        )
})
public class Comment extends BaseEntity implements Serializable {

    @ManyToOne(cascade = {}, optional=false)
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @JsonIgnore
    @ManyToOne(cascade = {}, optional=false)
    @JoinColumn(name="article_id")
    private Article article;

    private String content;

    @OneToMany(mappedBy = "comment",cascade={CascadeType.REMOVE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @OrderBy("createTime ASC")
    private List<Reply> replyList;

    public Comment(Long id){
        this.id = id;
    }
}
