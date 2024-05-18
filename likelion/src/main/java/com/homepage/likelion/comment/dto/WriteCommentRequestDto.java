package com.homepage.likelion.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WriteCommentRequestDto {
    @NotNull(message = "댓글 작성자 기본키를 작성해주세요")
    private Long memberId;

    @NotNull(message = "게시글의 기본키를 작성해주세요")
    private Long postsId;

    @NotNull(message = "댓글 내용이 비어있습니다")
    private String content;

}

