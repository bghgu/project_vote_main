package com.skhu.vote.model.Req;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by ds on 2018-01-30.
 */

@Data
@AllArgsConstructor
public class AuthCodeReq {
    private String code;

    public AuthCodeReq() {
        this.code = "";
    }
}
