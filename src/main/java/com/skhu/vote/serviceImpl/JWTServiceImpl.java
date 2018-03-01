package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.redis.Jwt;
import com.skhu.vote.repository.redis.JwtRepository;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.utils.SHA512EncryptUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
    private JwtRepository jwtRepository;

    /**
     * 토큰 생성
     * @param data
     * 토큰 데이터
     * @param key
     * 토큰 키값,
     * emc / voter
     * @param <T>
     * @return
     */
    @Override
    public <T> String createToken(final T data, final String key) {
        String jwt = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setId(key)
                .setExpiration(getTime())
                .claim(key, data)
                .signWith(SignatureAlgorithm.HS512, SHA512EncryptUtils.encrypt(SALT))
                .compact();
        saveJwt(jwt);
        return jwt;
    }

    private void saveJwt(final String jwt) {
        jwtRepository.save(new Jwt(jwt));
    }

    @Override
    public void logoutJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Jwt jwt = jwtRepository.findOne(request.getHeader("Authorization"));
        if(jwt != null) {
            jwtRepository.delete(jwt);
        }
    }

    /**
     * 토큰 저장된 데이터 반환
     * @param key
     * emc : 선관위용 토큰, voter : 유권자용 토큰
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


    /**
     * 토큰의 id 값 확인
     * @param key
     * emc / voter
     * @return
     */
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

    /**
     * 현재 시간 + 1시간
     * @return
     */
    @Override
    public Date getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 1);
        return cal.getTime();
    }
}
