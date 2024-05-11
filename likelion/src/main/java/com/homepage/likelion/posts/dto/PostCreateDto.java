package com.homepage.likelion.posts.dto;

import com.homepage.likelion.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class PostCreateDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Req{

        @NotBlank(message = "제목을 작성해주세요")
        private String title;

        @NotBlank(message = "내요을 작성해주세요")
        private String content;

        @NotBlank(message = "작성자 이름을 작성해주세요")
        private String postedUserName;

        @NotBlank(message = "비밀번호를 작성해주세요.")
        private String password;

        public Post toEntity(){
            return Post.builder()
                    .postedUserName(postedUserName)
                    .password(password)
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    // 게시글 작성 : postId, createdAt
    @Getter
    @Builder
    @NoArgsConstructor
    public static class CreatePost {
        private Long postId;
        private LocalDateTime createdAt;

        public CreatePost(Long postId, LocalDateTime createdAt){
            this.postId = postId;
            this.createdAt = createdAt;
        }
    }
}