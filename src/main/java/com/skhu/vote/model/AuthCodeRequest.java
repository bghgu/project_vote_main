package com.skhu.vote.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by ds on 2018-01-30.
 */

@Data
@AllArgsConstructor
public class AuthCodeRequest {
    private String code;

    public AuthCodeRequest() {
        this.code = "";
    }
}
