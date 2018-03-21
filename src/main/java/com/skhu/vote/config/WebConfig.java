package com.skhu.vote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by ds on 2018-02-02.
 */

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String[] EXCLUDE_ERROR_PATH = {
            "/no-session",
            "/no-token",
            "/token-error",
            "/unValued-token",
            "/token-session-error"
    };

    private static final String[] EXCLUDE_PATH = {
            "/emc/login",
            "/emc/clear",
            "/vote/access",
            "/vote/boardcast",
            "/vote/test"
    };

    /*//필터
    //@Value("${allowOrigins}")
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")//get,post,head
                .allowedOrigins("http://127.0.0.1:8100")
                .allowedOrigins("*")
                .allowCredentials(false)
                .maxAge(3600);
    }*/

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH);
    }

}
