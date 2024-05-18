package com.homepage.likelion.accounts.service;


import com.homepage.likelion.accounts.dto.AccountCreateDto;
import com.homepage.likelion.accounts.dto.AccountEnterDto;
import com.homepage.likelion.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    //회원가입
    ResponseEntity<CustomApiResponse<?>> signup(AccountCreateDto.Req req);

    //로그인
    ResponseEntity<CustomApiResponse<?>> login(AccountEnterDto req);

    //탈퇴
    ResponseEntity<CustomApiResponse<?>> withdraw(Long userId);
}
