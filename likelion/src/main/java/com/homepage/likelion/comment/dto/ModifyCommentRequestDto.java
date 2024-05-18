package com.homepage.likelion.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModifyCommentRequestDto {
    @NotNull(message = "회원의 기본키가 필요합니다")
    private Long memberId;
    @NotNull(message = "댓글의 기본키가 필요합니다")
    private Long commentId;
    @NotNull(message = "수정할 댓글의 내용을 적어주세요")
    private String content;
}
