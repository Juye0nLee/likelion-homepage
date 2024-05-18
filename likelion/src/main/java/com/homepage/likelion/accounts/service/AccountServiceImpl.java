package com.homepage.likelion.accounts.service;


import com.homepage.likelion.accounts.dto.AccountCreateDto;
import com.homepage.likelion.accounts.dto.AccountEnterDto;
import com.homepage.likelion.accounts.repository.AccountRepository;
import com.homepage.likelion.domain.Account;
import com.homepage.likelion.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    //회원가입
    public ResponseEntity<CustomApiResponse<?>> signup(AccountCreateDto.Req req) {

        //
        Account account = req.toEntity();

        //저장
        Account savedAccount = accountRepository.save(account);

        //응답 구현
        AccountCreateDto.CreateAccount createAccountResponse = new AccountCreateDto.CreateAccount(savedAccount.getId(),savedAccount.getCreateAt());
        CustomApiResponse<AccountCreateDto.CreateAccount> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),createAccountResponse,"회원가입 성공");
        return ResponseEntity.ok(res);
    }

    @Override
    //로그인
    // 1.  body로 받은 req가 DB에 존재하는지 검색
    // 2.
    public ResponseEntity<CustomApiResponse<?>> login(AccountEnterDto req){
        //찾기
        Optional<Account> optionalAccount = accountRepository.findByUserId(req.getUserId());
        //해당 아이디가 존재하지 않을 때
        if(optionalAccount.isEmpty()) {
            CustomApiResponse<Void> res =  CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        Account account = optionalAccount.get();
        //비밀번호가 일치하지 않을 때
        if(!account.getPassword().equals(req.getPassword())){
            CustomApiResponse<Void> res = CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value(), "비밀번호가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        AccountEnterDto.AccountEnter accountEnter = new AccountEnterDto.AccountEnter(account.getId(), account.getCreateAt());
        //로그인
        CustomApiResponse<AccountEnterDto.AccountEnter> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),accountEnter,"로그인 성공");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    //회원 탈퇴
    @Override
    public ResponseEntity<CustomApiResponse<?>> withdraw(Long userId) {
         //삭제
         accountRepository.deleteById(userId);
         Optional<Account> optionalAccount = accountRepository.findById(userId);
//         // 해당 userId를 가진 회원이 존재하지 않는 경우
//        if(optionalAccount.isEmpty()) {
//            CustomApiResponse<Void> res = CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "해당 userId를 가진 회원은 존재하지 않습니다.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
//        }
         CustomApiResponse<CustomApiResponse<?>> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),null,"회원탈퇴가 완료되었습니다." );
         return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}