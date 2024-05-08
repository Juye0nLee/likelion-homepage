package com.homepage.likelion.test.tmp;


import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity<CustomApiResponse<?>> handleError(HttpServletRequest request) {
        // HttpServletRequest 로 부터 status code 가져오기
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null) {
            int statusCode = Integer.parseInt(status.toString());

            //BAD_REQUEST (400)
            if(statusCode == HttpStatus.BAD_REQUEST.value()) //status == HttpStatus.BAD_REQUEST.value()
            {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new CustomApiResponse<>(HttpStatus.BAD_REQUEST.value(), null,"잘못된 요청입니다."));
            }
            //FORBIDDEN (403)
            else if(statusCode == HttpStatus.FORBIDDEN.value()) //status == HttpStatus.BAD_REQUEST.value()
            {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(new CustomApiResponse<>(HttpStatus.FORBIDDEN.value(), null,"접근이 금지되었습니다."));
            }
            //NOT_FOUND(404)
            else if(statusCode == HttpStatus.NOT_FOUND.value()) //status == HttpStatus.BAD_REQUEST.value()
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(HttpStatus.NOT_FOUND.value(), null,"요청경로를 찾을 수 없습니다."));
            }
            //METHOD_NOT_ALLOWED (405)
            else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) //status == HttpStatus.BAD_REQUEST.value()
            {
                return ResponseEntity
                        .status(HttpStatus.METHOD_NOT_ALLOWED)
                        .body(new CustomApiResponse<>(HttpStatus.METHOD_NOT_ALLOWED.value(), null,"허용되지 않은 메소드 입니다."));
            }
            //INTERNAL_SERVER_ERROR (500)
            else{
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new CustomApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null,"내부 서버 오류가 발생했습니다."));
            }
        }
        //status code에 따라서 응답을 다르게 내려줘야 한다.
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CustomApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null,"내부 서버 오류가 발생했습니다."));
    }
}
