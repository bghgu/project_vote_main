package com.skhu.vote.utils;

import java.util.Random;

/**
 * Created by ds on 2018-01-22.
 */

public class AuthCodeUtils {

    //인증번호 자리수 결정
    private static final int length = 4;

    public static int createCode() {
        String numStr = "1";
        String plusNumStr = "1";
        for (int i = 0; i < length; i++) {
            numStr += "0";
            if (i != length - 1) {
                plusNumStr += "0";
            }
        }
        Random random = new Random();
        int result = random.nextInt(Integer.parseInt(numStr)) + Integer.parseInt(plusNumStr);
        if (result > Integer.parseInt(numStr)) {
            result = result - Integer.parseInt(plusNumStr);
        }
        return result;
    }
}
