package com.skhu.vote.serviceImpl;

import com.skhu.vote.config.UnauthorizedException;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.utils.SHA512EncryptUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    @Override
    public <T> String createToken(final T data, final HttpServletRequest request) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 1);
        String jwt = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setId("emc")
                .setExpiration(cal.getTime())
                //.setExpiration(new Date())
                .claim("auth", data)
                //.signWith(SignatureAlgorithm.HS512, "1234")
                .signWith(SignatureAlgorithm.HS512, SHA512EncryptUtils.encrypt(SALT))
                .compact();
        HttpSession session = request.getSession(true);
        session.setAttribute("jwt", jwt);
        return jwt;
    }

    @Override
    public Map<String, Object> getToken(final String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SALT.getBytes("UTF-8"))
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            //throw new UnauthorizedException();
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
        return value;
    }

    @Override
    public String getAuthId() {
        return this.getToken("user").get("id").toString();
    }

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
}
