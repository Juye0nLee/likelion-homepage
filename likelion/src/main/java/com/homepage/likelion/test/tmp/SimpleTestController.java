package com.homepage.likelion.test.tmp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//SimpleTestController가 restroller..? 웹서비스임을 나타내는 어노테이션
@RequestMapping("/api/simpleText")
public class SimpleTestController {
    //GET http://localhost:8080/api/simpleText/success

    @GetMapping("/success")
    public String simpleTextSuccess() {
        return "안녕";
    }

    @GetMapping("/fail")
    public ResponseEntity<String> simpleTextFail() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패");
    }
}
