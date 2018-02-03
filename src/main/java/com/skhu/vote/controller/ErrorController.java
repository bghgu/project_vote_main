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

    @GetMapping("no-session")
    public ResponseEntity<DefaultResponse> noSession() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("no-session");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("session-error")
    public ResponseEntity<DefaultResponse> sessionError() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("session-error");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("no-token")
    public ResponseEntity<DefaultResponse> noToken() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("no-token");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("unValued-token")
    public ResponseEntity<DefaultResponse> unValuedToken() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("unValued-token");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("token-error")
    public ResponseEntity<DefaultResponse> tokenError() {
        DefaultResponse response = new DefaultResponse();
        response.setMsg("token-error");
        return new ResponseEntity<DefaultResponse>(response, HttpStatus.UNAUTHORIZED);
    }
}
