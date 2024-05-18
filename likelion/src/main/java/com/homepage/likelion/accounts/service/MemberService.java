package com.homepage.likelion.accounts.service;


import com.homepage.likelion.accounts.dto.MemberCreateDto;
import com.homepage.likelion.accounts.dto.MemberEnterDto;
import com.homepage.likelion.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    //회원가입
    ResponseEntity<CustomApiResponse<?>> signup(MemberCreateDto.Req req);

    //로그인
    ResponseEntity<CustomApiResponse<?>> login(MemberEnterDto req);

    //탈퇴
    ResponseEntity<CustomApiResponse<?>> withdraw(Long userId);
}
