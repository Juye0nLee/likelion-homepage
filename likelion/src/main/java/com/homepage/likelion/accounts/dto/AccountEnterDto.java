package com.homepage.likelion.accounts.dto;

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
public class AccountEnterDto {
        @NotBlank(message = "아이디를 입력해주세요")
        private String userId;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

        @Getter
        @NoArgsConstructor
        public static class AccountEnter {
            private Long id;
            private LocalDateTime createAt;
            public AccountEnter(Long id,LocalDateTime createAt) {
                this.createAt = createAt;
                this.id = id;
            }
        }
}
