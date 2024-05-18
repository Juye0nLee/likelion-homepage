package com.homepage.likelion.accounts.controller;



import com.homepage.likelion.accounts.dto.AccountCreateDto;
import com.homepage.likelion.accounts.dto.AccountEnterDto;
import com.homepage.likelion.accounts.service.AccountService;
import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class AccountController {
    private final AccountService accountService;
    //회원가입
    @PostMapping("/sign-up")
   public  ResponseEntity<CustomApiResponse<?>> signup(@Valid @RequestBody AccountCreateDto.Req req){
        ResponseEntity<CustomApiResponse<?>> result = accountService.signup(req);
        return result;
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(@RequestBody AccountEnterDto req){
        //AccountEnterDto res = new AccountEnterDto();
        ResponseEntity<CustomApiResponse<?>> result = accountService.login(req);
        return result;
    }

    //회원 탈퇴
    //Delete 메서드는 @RequestBody를 지원하지 않음
    @DeleteMapping("/withdraw")
    public ResponseEntity<CustomApiResponse<?>> withdraw(@RequestParam Long userId){
        ResponseEntity<CustomApiResponse<?>> result = accountService.withdraw(userId);
        return result;
    }
}
