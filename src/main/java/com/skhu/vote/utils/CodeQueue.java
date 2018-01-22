package com.skhu.vote.utils;

import java.util.HashSet;
/**
 * Created by ds on 2018-01-23.
 */
public class CodeQueue {

    //코드 저장 hashSet
    private static HashSet<String> hashCodeSet = new HashSet<>();

    //hashSet에 코드 삽입
    //이미 코드가 있을 경우 false
    public static boolean insertCode(String code) {
        if(hashCodeSet.contains(code)) {
            return false;
        }else {
            hashCodeSet.add(code);
            return true;
        }
    }

    //현재 저장된 코드 확인, 디버깅용
    public static void all() {
        for(String s : hashCodeSet) {
            System.out.println(s);
        }
    }

    //유권자 코드 로그인, 코드 검증, hashSet코드 삭제
    public static boolean verification(String code) {
        if(hashCodeSet.contains(code)) {
            hashCodeSet.remove(code);
            return true;
        }else {
            return false;
        }
    }
}
