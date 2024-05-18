package com.homepage.likelion.domain;

import com.homepage.likelion.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="COMMENTS")
@Builder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="COMMENTS_ID")
    private Long id;

    @Column(name= "COMMENT_CONTENT")
    private  String content;

    @ManyToOne //댓글이 다에 해당하므로
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne //댓글이 다에 해당하므로
    @JoinColumn(name="POSTS_ID")
    private  Post post;


    public void  createComment(Post post, Member member) {
        this.post = post;
        this.member = member;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
