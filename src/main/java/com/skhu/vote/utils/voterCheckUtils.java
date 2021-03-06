package com.skhu.vote.utils;

import com.skhu.vote.domain.USER;
import com.skhu.vote.model.Res.DefaultRes;

/**
 * Created by ds on 2018-01-30.
 */
public class voterCheckUtils {
    public static DefaultRes check(final USER user) {
        DefaultRes response = new DefaultRes();
        if (user == null) response.setMsg("등록된 학생이 없습니다.");
        else {
            if (user.getUserType() == 0) response.setMsg("유권자가 아닙니다.");
            else {
                if (user.getConfirmCheck() == 1) response.setMsg("이미 투표를 진행했습니다.");
            }
        }
        return response;
    }
}
