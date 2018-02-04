package com.skhu.vote.model.Res;

import com.skhu.vote.model.StatusEnum;
import lombok.Data;

/**
 * Created by ds on 2018-01-29.
 */

@Data
public class DefaultRes {

    private StatusEnum status;
    private Object data;
    private String msg;

    public DefaultRes() {
        this.status = StatusEnum.FAIL;
        this.data = null;
        this.msg = null;
    }

    public DefaultRes(final StatusEnum status) {
        this.status = status;
        this.data = null;
        this.msg = null;
    }

}
