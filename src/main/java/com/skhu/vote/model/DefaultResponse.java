package com.skhu.vote.model;

import lombok.Data;

/**
 * Created by ds on 2018-01-29.
 */

@Data
public class DefaultResponse {

    private StatusEnum status;
    private Object data;
    private String msg;

    public DefaultResponse() {
        this.status = StatusEnum.FAIL;
        this.data = null;
        this.msg = null;
    }

}
