package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.LoginAdmin;
import com.skhu.vote.model.Req.LoginReq;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.repository.redis.EmcRepository;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ds on 2018-02-02.
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmcRepository emcRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public LoginAdmin login(final LoginReq loginReq) {
        //SHA512EncryptUtils.encrypt)
        ADMIN admin = adminRepository.findByIdAndPassword(loginReq.getId(), loginReq.getPassword());
        //아이디 비밀번호 틀림
        if(admin == null) {
            return null;
        }else {
            LoginAdmin loginAdmin = createUser(admin);
            //로그인 중
            //세션 안됨
            /*if(sessionService.isSession(admin.getId())) {
                return null;
            }else {
                LoginAdmin loginAdmin = createUser(admin);
                sessionService.setSession(admin.getId(), loginAdmin);
                return loginAdmin;
            }*/
            System.out.println(loginAdmin.toString());

            return loginAdmin;
        }
    }

    private LoginAdmin createUser(ADMIN admin) {
        LoginAdmin loginAdmin = new LoginAdmin(admin);
        if(loginAdmin.getType() == 2) {
            //선관위원장
            loginAdmin.setRoles("ROLE_MASTER");
        }else {
            //선관위
            loginAdmin.setRoles("ROLE_ADMIN");
        }
        return loginAdmin;
    }

    /**
     * 로그 아웃
     */
    @Override
    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        //sessionService.removeSession(jwtService.getAuthId("emc"));
        //sessionService.removeSession(request.getHeader("Authorization"));
    }

}
