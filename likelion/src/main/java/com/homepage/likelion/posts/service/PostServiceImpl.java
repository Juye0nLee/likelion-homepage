package com.homepage.likelion.posts.service;

import com.homepage.likelion.domain.Post;
import com.homepage.likelion.posts.dto.PostCreateDto;
import com.homepage.likelion.posts.dto.PostListDto;
import com.homepage.likelion.posts.dto.PostUpdateDto;
import com.homepage.likelion.posts.repository.PostRepository;
import com.homepage.likelion.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    //게시글 작성
    public ResponseEntity<CustomApiResponse<?>> createPost(PostCreateDto.Req req) {

        //방법 1. toEntity
        Post post = req.toEntity();


        //방법 2. builder 패턴 사용
        //

        //저장
        Post savedPost = postRepository.save(post);

        //게시글 정보를 포함한 응답
        PostCreateDto.CreatePost createPostResponse = new PostCreateDto.CreatePost(savedPost.getId(),savedPost.getUpdateAt());
        CustomApiResponse<PostCreateDto.CreatePost> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),createPostResponse,"게시글이 작성되었습니다.");
        return ResponseEntity.ok(res);
        //return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> modifyPost(Long postId, PostUpdateDto.Req req) {
        //게시물 검색
        //postId로 검색
        Optional<Post> optionalPost = postRepository.findById(postId);

        //게시물 업데이트
        //검색한 결과의 포스트를 가져옴
        Post post = optionalPost.get();
        //제목 변경
        post.changeTitle(req.getTitle());
        //내용 변경
        post.changeContent(req.getContent());
        //작성자 변경
        post.changeUserName(req.getPostedUserName());

        postRepository.flush(); //변경 사항을 데이터베이스에 즉시 적용

        //수정된 게시글 정보가 답긴 응답 구현하기
        //data = 수정된 시간 받아오기 -> 왜? 응답의 구성요소 status, data, message 중 data 부분에 넣어주기 위해서(달라질 수 있음)
        PostUpdateDto.UpdatePost data = new PostUpdateDto.UpdatePost(post.getUpdateAt());

        CustomApiResponse<PostUpdateDto.UpdatePost> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),data,"게시글이 수정되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @Override
    public ResponseEntity<CustomApiResponse<?>> getOnePost(Long postId) {
        //postId로 게시물 검색하고 그 결과(postId에 해당하는 게시물)를 optionalPost 변수에 넣기
        Optional<Post> optionalPost = postRepository.findById(postId);
        //찾는 postId에 해당하는 게시물이 없으면
        if(optionalPost.isEmpty()){
            //NOT_FOUND 에러 발생
            CustomApiResponse<Void> res = CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value(), "해당하는 게시글을 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        Post post = optionalPost.get();
        //찾는 게시물에 대한 데이터를 postResponse 변수에 넣어줘서 응답에 넣어줌
        PostListDto.PostResponse postResponse = new PostListDto.PostResponse(
                post.getId(), //게시물 id 불러오기
                post.getPostedUserName(), //게시물 작성자 불러오기
                post.getTitle(), //게시물 제목 불러오기
                post.getContent(), //게시물 내용 불러오기
                post.getUpdateAt() //게시물 마지막 업데이트 시작 불러오기
        );

        //응답 구현
        CustomApiResponse<PostListDto.PostResponse> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),
                postResponse, "게시글 조회 성공");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> getAllPost() {
        List<Post> posts = postRepository.findAll(); //postRepository에 있는 모든 게시물 데이터를 posts 리스트에 넣어줌
        List<PostListDto.PostResponse> postResponses = new ArrayList<>();

        for(Post post : posts){
            postResponses.add(PostListDto.PostResponse.builder()
                    .postId(post.getId())
                    .postedUserName(post.getPostedUserName())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .updatedAt(post.getUpdateAt())
                    .build());
        }

        PostListDto.SearchPostsRes searchPostsRes = new PostListDto.SearchPostsRes(postResponses);
        //응답 구현
        CustomApiResponse<PostListDto.SearchPostsRes> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(), searchPostsRes,"게시물 전체 조회 성공");
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }
}
