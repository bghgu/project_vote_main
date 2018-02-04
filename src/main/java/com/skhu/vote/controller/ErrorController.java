package com.skhu.vote.controller;

import com.skhu.vote.model.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ds on 2018-02-03.
 */

@RestController()
public class ErrorController {


    /**
     * 세션 오류
     * @return
     */
    @GetMapping("no-session")
    public ResponseEntity<DefaultResponse> noSession() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("no-session");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 세션에 저장된 토큰 확인
     * @return
     */
    @GetMapping("session-error")
    public ResponseEntity<DefaultResponse> sessionError() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("session-token-error");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 토큰 넘어왔는지 확인
     * @return
     */
    @GetMapping("no-token")
    public ResponseEntity<DefaultResponse> noToken() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("no-token");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 세션에 저장된 토큰과 request 받은 토큰 비교
     * @return
     */
    @GetMapping("unValued-token")
    public ResponseEntity<DefaultResponse> unValuedToken() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("unValued-token");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 토큰 검증
     * @return
     */
    @GetMapping("token-error")
    public ResponseEntity<DefaultResponse> tokenError() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("token-error");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }
}
