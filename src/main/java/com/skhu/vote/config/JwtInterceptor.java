package com.skhu.vote.config;

import com.skhu.vote.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    private static final String ORIGIN = "Origin";
    private static final String AC_REQUEST_METHOD = "Access-Control-Request-Method";
    private static final String AC_REQUEST_HEADERS = "Access-Control-Request-Headers";

    private static final String AC_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String AC_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String AC_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    private CorsData corsData;

    private String origin;
    private String allowMethods;
    private String allowHeaders;

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setAllowMethods(String allowMethods) {
        this.allowMethods = allowMethods;
    }

    public void setAllowHeaders(String allowHeaders) {
        this.allowHeaders = allowHeaders;
    }

    private static final String HEADER = "Authorization";

    @Autowired
    private JwtService jwtService;

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
        this.corsData = new CorsData(request);

        if(this.corsData.isPreflighted()) {
            response.setHeader(AC_ALLOW_ORIGIN, origin);
            response.setHeader(AC_ALLOW_METHODS, allowMethods);
            response.setHeader(AC_ALLOW_HEADERS, allowHeaders);

            return false;
        }

        final String token = request.getHeader(HEADER);

        //헤더값이 없을 경우
        if(token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no-token");
            //response.sendRedirect("/no-token");
            return false;
        }
        //헤더값은 있지만 토큰값이 잘못된 경우
        if(!jwtService.isValuedToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token-error");
            //response.sendRedirect("/token-error");
            return false;
        }
        //해당 토큰값의 세션 여부
        if(!jwtService.isJwt(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token-session-error");
            //response.sendRedirect("/token-session-error");
            return false;
        }
        return true;
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

    class CorsData {

        private String origin;
        private String requestMethods;
        private String requestHeaders;

        CorsData(HttpServletRequest request) {
            this.origin = request.getHeader(ORIGIN);
            this.requestMethods= request.getHeader(AC_REQUEST_METHOD);
            this.requestHeaders = request.getHeader(AC_REQUEST_HEADERS);
        }

        public boolean hasOrigin(){
            return origin != null && !origin.isEmpty();
        }

        public boolean hasRequestMethods(){
            return requestMethods != null && !requestMethods.isEmpty();
        }

        public boolean hasRequestHeaders(){
            return requestHeaders != null && !requestHeaders.isEmpty();
        }

        public String getOrigin() {
            return origin;
        }

        public String getRequestMethods() {
            return requestMethods;
        }

        public String getRequestHeaders() {
            return requestHeaders;
        }

        public boolean isPreflighted() {
            return hasOrigin() && hasRequestHeaders() && hasRequestMethods();
        }

        public boolean isSimple() {
            return hasOrigin() && !hasRequestHeaders();
        }
    }

}
