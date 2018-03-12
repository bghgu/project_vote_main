package com.skhu.vote.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ds on 2018-02-06.
 */

public class BlockHashUtils {

    @Value("${key}")
    private static String key;

    public static String hashCode(Object... values) {
        return gethashCode(values);
    }

    private static String gethashCode(Object a[]) {
        if (a == null)
            return null;

        StringBuilder result = new StringBuilder();

        for (Object element : a) result.append(element == null ? 0 : element);

        return SHA512EncryptUtils.encrypt(result.toString());
    }
}
