package com.homepage.likelion.accounts.controller;



import com.homepage.likelion.accounts.dto.MemberCreateDto;
import com.homepage.likelion.accounts.dto.MemberEnterDto;
import com.homepage.likelion.accounts.service.MemberService;
import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {
    private final MemberService memberService;
    //회원가입
    @PostMapping("/sign-up")
   public  ResponseEntity<CustomApiResponse<?>> signup(@Valid @RequestBody MemberCreateDto.Req req){
        ResponseEntity<CustomApiResponse<?>> result = memberService.signup(req);
        return result;
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(@RequestBody MemberEnterDto req){
        //AccountEnterDto res = new AccountEnterDto();
        ResponseEntity<CustomApiResponse<?>> result = memberService.login(req);
        return result;
    }

    //회원 탈퇴
    //Delete 메서드는 @RequestBody를 지원하지 않음
    @DeleteMapping("/withdraw")
    public ResponseEntity<CustomApiResponse<?>> withdraw(@RequestParam Long userId){
        ResponseEntity<CustomApiResponse<?>> result = memberService.withdraw(userId);
        return result;
    }
}
