package com.skhu.vote.utils;

import java.util.Random;

/**
 * Created by ds on 2018-01-23.
 */

//테스트
public class Test {

    final static int size = 6;

    public static void main(String[] args) {
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < size; i++){
            // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
            if(rnd.nextBoolean()){
                buf.append((char)((int)(rnd.nextInt(26))+97));
            }else{
                buf.append((rnd.nextInt(10)));
            }
        }
        System.out.println(buf.toString());
    }
}
