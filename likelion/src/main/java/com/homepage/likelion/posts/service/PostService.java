package com.homepage.likelion.posts.service;

import com.homepage.likelion.posts.dto.PostCreateDto;
import com.homepage.likelion.posts.dto.PostUpdateDto;
import com.homepage.likelion.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {

    //게시물 생성 -> PostServiceImpl에서 구현
    ResponseEntity<CustomApiResponse<?>> createPost(PostCreateDto.Req req);

    //게시물 수정 -> PostServiceImpl에서 구현
    ResponseEntity<CustomApiResponse<?>> modifyPost(Long postId, PostUpdateDto.Req req);

    ResponseEntity<CustomApiResponse<?>> getOnePost(Long postId);

}
