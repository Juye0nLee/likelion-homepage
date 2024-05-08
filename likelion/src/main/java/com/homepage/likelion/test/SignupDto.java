package com.homepage.likelion.test;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    @NotEmpty(message = "userId는 필수값입니다.")
    private String userId;

    @Email(message = "이메일 형식을 맞춰주세요")
    private String email;
    private String password;



}
