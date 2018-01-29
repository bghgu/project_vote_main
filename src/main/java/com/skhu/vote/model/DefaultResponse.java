package com.skhu.vote.model;

import lombok.Data;

/**
 * Created by ds on 2018-01-29.
 */

@Data
public class DefaultResponse {

    private Status status;
    private Object data;
    private String msg;

    public DefaultResponse() {
        this.status = Status.FAIL;
        this.data = null;
        this.msg = null;
    }

}
