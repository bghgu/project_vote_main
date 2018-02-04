package com.skhu.vote.serviceImpl;

import com.skhu.vote.config.UnauthorizedException;
import com.skhu.vote.model.LoginAdmin;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.SessionService;
import com.skhu.vote.utils.SHA512EncryptUtils;
import io.jsonwebtoken.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ds on 2018-02-02.
 */

@Service
public class JWTServiceImpl implements JwtService{

    @Value("${JWT.SALT}")
    private String SALT;

    @Autowired
    private SessionService sessionService;

    /**
     * 토큰 생성
     * @param data
     * @param <T>
     * @return
     */
    @Override
    public <T> String createToken(final T data) {
        String jwt = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setId("emc")
                //.setExpiration(getTime())
                //.setExpiration(new Date())
                .claim("auth", data)
                //.signWith(SignatureAlgorithm.HS512, "1234")
                .signWith(SignatureAlgorithm.HS512, SHA512EncryptUtils.encrypt(SALT))
                .compact();
        sessionService.setSession(jwt, jwt);
        return jwt;
    }

    /**
     * 토큰 저장된 데이터 반환
     * @param key
     * @return
     */
    @Override
    public Map<String, Object> getToken(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SHA512EncryptUtils.encrypt(SALT))
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            //throw new UnauthorizedException();
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
        return value;
    }


    @Override
    public String getAuthId(final String key) {
        return this.getToken(key).get("id").toString();
    }

    /**
     * 토큰 유효성 검사
     * @param jwt
     * @return
     */

    @Override
    public boolean isValuedToken(final String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SHA512EncryptUtils.encrypt(SALT))
                    .parseClaimsJws(jwt);
            return true;
        }catch (Exception e) {
            return false;
            //throw new UnauthorizedException();
        }
    }

    @Override
    public Date getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 1);
        return cal.getTime();
    }
}
