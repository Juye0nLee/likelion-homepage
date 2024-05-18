package com.homepage.likelion.comment.controller;

import com.homepage.likelion.comment.dto.DeleteCommentRequestDto;
import com.homepage.likelion.comment.dto.ModifyCommentRequestDto;
import com.homepage.likelion.comment.dto.WriteCommentRequestDto;
import com.homepage.likelion.comment.service.CommentsService;
import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> writeComment(
            @RequestBody @Valid
            WriteCommentRequestDto requestDto){
        return commentsService.writeComment(requestDto);
    }

    @PutMapping
    public ResponseEntity<CustomApiResponse<?>> modifyComment(
            @RequestBody @Valid ModifyCommentRequestDto requestDto){
        return commentsService.modifyComment(requestDto);
    }

    @DeleteMapping
    public ResponseEntity<CustomApiResponse<?>> deleteComment(
            @RequestBody @Valid DeleteCommentRequestDto requestDto
    ){
        return commentsService.deleteComment(requestDto);
    }
}
