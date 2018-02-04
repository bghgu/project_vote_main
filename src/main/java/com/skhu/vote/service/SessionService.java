package com.skhu.vote.service;

/**
 * Created by ds on 2018-02-04.
 */
public interface SessionService {
    <T> Object getSession(final String key);
    <T> void setSession(final String key, final T value);
    boolean isSession(final String key);
    void removeSession(final String key);
}
