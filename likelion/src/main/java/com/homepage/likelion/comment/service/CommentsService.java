package com.homepage.likelion.comment.service;

import com.homepage.likelion.comment.dto.*;
import com.homepage.likelion.comment.repository.CommentsRepository;
import com.homepage.likelion.domain.Comment;
import com.homepage.likelion.domain.Member;
import com.homepage.likelion.domain.Post;
import com.homepage.likelion.members.repository.MemberRepository;
import com.homepage.likelion.posts.repository.PostRepository;
import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final MemberRepository memberRepository;
    private  final PostRepository postRepository;

    @Transactional
    //1.댓글작성
    public ResponseEntity<CustomApiResponse<?>> writeComment(WriteCommentRequestDto dto) {
        //1.댓글 작성자가 DB에 존재하는지 확인
        Optional<Member> findMember = memberRepository.findById(dto.getMemberId());
        if(findMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.FORBIDDEN.value(),"잘못된 접근 입니다."));
        }
        //2. 게시글이 실제로 존재하는 게시글인지 확인
        Optional<Post> findPost = postRepository.findById(dto.getPostsId());
        if(findPost.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value(), "해당 게시글이 존해하지 않습니다."));
        }

        //3. 모든 확인 절차가 끝나면 댓글 생성 및 연관관계 생성
        Comment createdComment = Comment.builder().content(dto.getContent()).build();
        createdComment.createComment(findPost.get(),findMember.get());
        //댓글 엔티티 저장
        commentsRepository.save(createdComment);

        //사용자에게 반환될 응답 DTO 생성
        WriteCommentResponseDto requestDto = WriteCommentResponseDto.builder()
                .commentsId(createdComment.getId())
                .updateAt(createdComment.getUpdateAt())
                .build();

        //응답
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(),requestDto,"댓글이 작성되었습니다"));

    }

    //댓글 수정
    @Transactional
    public ResponseEntity<CustomApiResponse<?>> modifyComment(ModifyCommentRequestDto requestDto) {
        Optional<Comment> findComment = commentsRepository.findById(requestDto.getCommentId());
        if (findComment.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithout(
                            HttpStatus.BAD_REQUEST.value(),
                            "수정하려는 댓글이 존재하지 않거나 잘못된 요청입니다."));
        }

        Long commentMemberId = findComment.get().getMember().getId();
        if (commentMemberId != requestDto.getMemberId()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(CustomApiResponse.createFailWithout(
                            HttpStatus.FORBIDDEN.value(),
                            "잘못된 요청입니다."
                    ));
        }

        Comment comment = findComment.get();
        comment.changeContent(requestDto.getContent());

        ModifyCommentResponseDto responseDto = ModifyCommentResponseDto.builder().updatedAt(comment.getUpdateAt()).build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(
                        HttpStatus.OK.value(),
                        responseDto,
                        "댓글이 수정되었습니다."
                ));
    }

    @Transactional
    public ResponseEntity<CustomApiResponse<?>> deleteComment(DeleteCommentRequestDto requestDto) {
        Optional<Comment> findComment = commentsRepository.findById(requestDto.getCommentId());
        if(findComment.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithout(
                            HttpStatus.BAD_REQUEST.value(),
                            "해당 댓글이 존재하지 않거나, 잘못된 요청입니다."
                    ));
        }

        if(findComment.get().getMember().getId() != requestDto.getMemberId()){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(CustomApiResponse.createFailWithout(
                            HttpStatus.FORBIDDEN.value(),
                            "잘못된 요청입니다."
                    ));
        }

        commentsRepository.delete(findComment.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(
                        HttpStatus.OK.value(),
                        null,
                        "댓글이 삭제되었습니다."
                ));
    }
}

