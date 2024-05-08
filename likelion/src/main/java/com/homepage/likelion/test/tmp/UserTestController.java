package com.homepage.likelion.test.tmp;

import com.homepage.likelion.test.dto.SignupDto;
import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tmp/api/user")
public class UserTestController {
    //POST http://localhost:8080/api/user/signup
    @PostMapping("/signup")
    public ResponseEntity<CustomApiResponse<?>> signup(@Valid @RequestBody SignupDto dto){

        //회원가입
        System.out.println(dto.getEmail());

        //응답
        CustomApiResponse<Object> responseBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "회원가입에 성공했습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
