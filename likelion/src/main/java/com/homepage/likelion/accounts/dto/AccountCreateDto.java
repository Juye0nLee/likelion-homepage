package com.homepage.likelion.accounts.dto;

import com.homepage.likelion.domain.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

public class AccountCreateDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Req{

        @Email(message = "이메일 형식을 맞춰주세요")
        @NotBlank(message = "이메일을 입력해주세요")
        private String email;

        @NotBlank(message = "전화번호를 입력해주세요")
        //형태를 검증하는 어노테이션(3자리-4자리-4자리)
        @Pattern(regexp = "\\d{3}\\d{4}\\d{4}",message = "전화번호 형식을 맞춰주세요")
        private String phone;

        @NotBlank(message = "아이디를 입력해주세요")
        private String userId;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;


        public Account toEntity(){
            return Account.builder()
                    .email(email)
                    .password(password)
                    .phone(phone)
                    .userId(userId)
                    .build();
        }
/*        public String checkPhone(String phone){
            //휴대폰 번호 표준 정규식 3자리-4자리-4자리
            String regEx = "(\\\\d{3})(\\\\d{4})(\\\\d{4})";
            return phone.replaceAll(regEx, "");
        }*/
    }

    @Getter
    @NoArgsConstructor
    @Builder
    //계정 생성 : id, createdAt
    public static class CreateAccount{
        private Long id;
        private LocalDateTime createAt;

        public CreateAccount(Long id, LocalDateTime createAt) {
            this.id = id;
            this.createAt = createAt;
        }
    }
}
