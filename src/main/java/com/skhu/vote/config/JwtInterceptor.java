package com.skhu.vote.config;

import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ds on 2018-02-02.
 */

/**
 * 스프링의 Dispatcher 서블릿이 컨트롤러를 호출할 때 전, 후에 끼어듭니다.
 * 그러므로 스프링 컨텍스트 내부에 존재하게 됩니다.
 * 인터셉터는 여러개를 사용할 수 있으며, 실행 순서는 <mvc:interceptors> 에 나오는 순서 입니다.
 * 인터셉터를 주로 사용하는 곳은 로그인 체크, 권한 체크, 프로그램 실행시간 계산 작업 로그 처리, 업로드 파일 처리 등에 많이 사용됩니다.
 */

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static final String HEADER = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Autowired
    private SessionService sessionService;

    /**
     * 컨트롤러 메소드 실행 직전에 수행
     * true 를 반환하면 계속 진행이 되고  false 를 리턴하면 실행 체인(다른 인터셉터, 컨트롤러 실행)이 중지되고 반환
     * 필터의 응답 처리가 있다면 그것은 실행이 된다.
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER);
        HttpSession session = request.getSession(false);

        //System.out.println(jwtService.getAuthId(token));

        //세션 확인
        if(session == null) {
            response.sendRedirect("/no-session");
            return false;
        }
        //토큰 넘어왔는지 확인
        if(token == null) {
            response.sendRedirect("/no-token");
            return false;
        }
        //토큰 검증
        if(!jwtService.isValuedToken(token)) {
            response.sendRedirect("/token-error");
            return false;
        }
        //세션에 저장된 토큰 확인
        if(!sessionService.isSession(token)) {
            response.sendRedirect("/session-error");
            return false;
        }
        //세션에 저장된 토큰과 request 받은 토큰 비교
        if(!sessionService.getSession(token).equals(token)) {
            response.sendRedirect("/unValued-token");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 컨트롤러 메소드 실행 직후에 실행
     * View 페이지가 렌더링 되기전에 ModelAndView 객체를 조작 가능
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * View 페이지가 렌더링 되고 난 후 에 실행
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    /**
     * Servlet 3.0 부터 비동기 요청이 가능
     * 비동기 요청시 postHandle와 afterCompletion 은 실행되지 않고, 이 메소드가 실행
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    }

}
