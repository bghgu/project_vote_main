package com.skhu.vote.model.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ds on 2018-02-02.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {
    private String id;
    private String password;
}
