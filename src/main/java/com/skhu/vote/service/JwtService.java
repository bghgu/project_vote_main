package com.skhu.vote.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by ds on 2018-02-02.
 */

public interface JwtService {
    <T> String createToken(final T data, final String key);
    Map<String, Object> getToken(final String key);
    String getAuthId(final String key);
    boolean isValuedToken(final String jwt);
    Date getTime();
    void logoutJwt();
}
