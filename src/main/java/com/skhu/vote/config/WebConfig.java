package com.skhu.vote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ds on 2018-02-02.
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String[] EXCLUDE_ERROR_PATH = {
            "/no-session",
            "/session-error",
            "/no-token",
            "/token-error",
            "/unValued-token"
    };

    private static final String[] EXCLUDE_PATH = {
            "/emc/login",
            "/vote/access"
    };

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH)
                .excludePathPatterns(EXCLUDE_ERROR_PATH);
    }

    //필터
    //@Value("${allowOrigins}")
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")//get,post,head
                //.allowedOrigins()
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
