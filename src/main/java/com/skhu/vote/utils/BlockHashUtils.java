package com.skhu.vote.utils;

/**
 * Created by ds on 2018-02-06.
 */

public class BlockHashUtils {

    public static String hashCode(Object... values) {
        return gethashCode(values);
    }

    private static String gethashCode(Object a[]) {
        if (a == null)
            return null;

        StringBuilder result = new StringBuilder();

        for (Object element : a)
            result.append(element == null ? 0 : element);
        String res = SHA512EncryptUtils.encrypt(result.toString());
        return res;
    }
}
