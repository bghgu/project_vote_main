package com.skhu.vote.serviceImpl;

import com.skhu.vote.service.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ds on 2018-02-04.
 */
@Service
public class SessionServiceImpl implements SessionService{

    /**
     * 세션 생성
     * @param key
     * @param value
     * @param <T>
     */
    @Override
    public <T> void setSession(final String key, final T value) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
    }

    /**
     * 세션 획득
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public <T> Object getSession(final String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession(true);
        return session.getAttribute(key);
    }

    /**
     * 세션 체크
     * 있으면 true, 없으면 false
     * @param key
     * @return
     */
    @Override
    public boolean isSession(final String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession(true);
        if(session.getAttribute(key) == null) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * 세션 제거
     * @param key
     */
    @Override
    public void removeSession(final String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession(true);
        session.removeAttribute(key);
    }
}
