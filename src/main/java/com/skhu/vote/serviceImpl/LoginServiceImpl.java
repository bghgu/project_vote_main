package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.LoginAdmin;
import com.skhu.vote.model.Req.LoginReq;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.LoginService;
import com.skhu.vote.service.SessionService;
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
    private SessionService sessionService;

    @Autowired
    JwtService jwtService;

    @Override
    public LoginAdmin login(final LoginReq loginReq) {
        //SHA512EncryptUtils.encrypt)
        ADMIN admin = adminRepository.findByIdAndPassword(loginReq.getId(), loginReq.getPassword());
        if(admin == null) {
            return null;
        }else {
            LoginAdmin loginAdmin = createUser(admin);
            if(sessionService.isSession(loginAdmin.getId())) {
                return new LoginAdmin(true);
            }else {
                sessionService.setSession(loginAdmin.getId(), loginAdmin);
                return createUser(admin);
            }
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
        sessionService.removeSession(jwtService.getAuthId("emc"));
        sessionService.removeSession(request.getHeader("Authorization"));
    }

}
