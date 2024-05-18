package com.homepage.likelion.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class WriteCommentResponseDto {
    private Long commentsId;
    private LocalDateTime updateAt;
/*        public void createComment(Member member, Post post) {
            this.mem
        }*/
}
