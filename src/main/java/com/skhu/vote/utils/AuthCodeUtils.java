package com.skhu.vote.utils;

import java.util.Random;

/**
 * Created by ds on 2018-01-22.
 */

public class AuthCodeUtils {

    //인증번호 자리수 결정
    private static final int length = 8;

    public static int createIntCode() {
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

    public static String createStringCode() {
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < length; i++){
            // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
            if(rnd.nextBoolean()){
                buf.append((char)((int)(rnd.nextInt(26))+97));
            }else{
                buf.append((rnd.nextInt(10)));
            }
        }
        return buf.toString();
    }
}
