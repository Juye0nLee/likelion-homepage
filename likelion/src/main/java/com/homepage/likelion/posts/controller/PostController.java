package com.homepage.likelion.posts.controller;

import com.homepage.likelion.posts.dto.PostCreateDto;
import com.homepage.likelion.posts.dto.PostUpdateDto;
import com.homepage.likelion.posts.service.PostService;
import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
public class PostController {
    private final PostService postService;

    //게시글 작성
    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> createPost(
            @Valid @RequestBody PostCreateDto.Req req) {
        ResponseEntity<CustomApiResponse<?>> result = postService.createPost(req);
        return result;
    }

    //수정이니까 Put으로
    //postId는 경로 변수로 실제로 요청된 게시물의 ID
    @PutMapping("/{postId}")
    public ResponseEntity<CustomApiResponse<?>> modifyPost(
            //요청 URL의 경로 변수 값을 메소드 값을 메소드 파마미터에 바인딩할 때 사용
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateDto.Req req){

        //postService에서 modifyPost에 대한 응답을 result 변수로 받아옴
        ResponseEntity<CustomApiResponse<?>> result = postService.modifyPost(postId, req);
        return result;
    }

    //전체 게시글 조회
    @GetMapping("/all")
    public ResponseEntity<CustomApiResponse<?>> getAllPosts() {
        ResponseEntity<CustomApiResponse<?>> result = postService.getAllPost();
        return result;
    }

    //게시글 1개 조회
    @GetMapping
    public ResponseEntity<CustomApiResponse<?>> getOnePost(
            @RequestParam("postId") Long postId){
        ResponseEntity<CustomApiResponse<?>> result = postService.getOnePost(postId);
        return result;

    }


}
