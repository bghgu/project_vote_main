package com.skhu.vote.service;

import java.util.Map;

/**
 * Created by ds on 2018-02-02.
 */

public interface JwtService {
    <T> String createToken(final T data);
    Map<String, Object> getToken(final String key);
    String getAuthId();
    boolean isValuedToken(final String jwt);
}
