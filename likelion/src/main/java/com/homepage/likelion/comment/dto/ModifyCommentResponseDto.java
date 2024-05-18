package com.homepage.likelion.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ModifyCommentResponseDto
{
    private LocalDateTime updatedAt;

}
