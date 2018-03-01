package com.skhu.vote.controller;

import com.skhu.vote.model.Res.DefaultRes;
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
     * 헤더값에 토큰 넘어왔는지 확인
     * @return
     */
    @GetMapping("no-token")
    public ResponseEntity<DefaultRes> noToken() {
        DefaultRes response = new DefaultRes();
        response.setMsg("no-token");
        return new ResponseEntity<DefaultRes>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 토큰 검증
     * @return
     */
    @GetMapping("token-error")
    public ResponseEntity<DefaultRes> tokenError() {
        DefaultRes response = new DefaultRes();
        response.setMsg("token-error");
        return new ResponseEntity<DefaultRes>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 세션에 저장된 토큰 확인
     * @return
     */
    @GetMapping("token-session-error")
    public ResponseEntity<DefaultRes> sessionError() {
        DefaultRes response = new DefaultRes();
        response.setMsg("token-session-errorr");
        return new ResponseEntity<DefaultRes>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 세션 오류
     * @return
     */
    @GetMapping("no-session")
    public ResponseEntity<DefaultRes> noSession() {
        System.out.println("no-session");
        DefaultRes response = new DefaultRes();
        response.setMsg("no-session");
        return new ResponseEntity<DefaultRes>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 세션에 저장된 토큰과 request 받은 토큰 비교
     * @return
     */
    @GetMapping("unValued-token")
    public ResponseEntity<DefaultRes> unValuedToken() {
        System.out.println("unValued-token");
        DefaultRes response = new DefaultRes();
        response.setMsg("unValued-token");
        return new ResponseEntity<DefaultRes>(response, HttpStatus.UNAUTHORIZED);
    }


}
