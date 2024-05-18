package com.homepage.likelion.members.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEnterDto {
        @NotBlank(message = "아이디를 입력해주세요")
        private String userId;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

        @Getter
        @NoArgsConstructor
        public static class MemberEnter {
            private Long id;
            private LocalDateTime createAt;
            public MemberEnter(Long id,LocalDateTime createAt) {
                this.createAt = createAt;
                this.id = id;
            }
        }
}
