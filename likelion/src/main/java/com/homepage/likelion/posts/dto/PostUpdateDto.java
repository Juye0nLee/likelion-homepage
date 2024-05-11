package com.homepage.likelion.posts.dto;

import lombok.*;

import java.time.LocalDateTime;


public class PostUpdateDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private Long postId;
        private String title;
        private String content;
        private String postedUserName;
        private String password;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    public static class UpdatePost{
        //수정된 시간
        private LocalDateTime updateAt;

        public UpdatePost(LocalDateTime updateAt) {
            this.updateAt = updateAt;
        }

    }
}
