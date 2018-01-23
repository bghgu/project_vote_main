package com.skhu.vote.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created by ds on 2018-01-23.
 */

//테스트
public class Test {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        JSONPObject jsonpObject = new JSONPObject("1","2");
        System.out.println(jsonpObject.getFunction());
        System.out.println(jsonpObject.getSerializationType());
        System.out.println(jsonpObject.getValue());
        System.out.println(jsonpObject.toString());
        System.out.println(jsonpObject);
    }
}
