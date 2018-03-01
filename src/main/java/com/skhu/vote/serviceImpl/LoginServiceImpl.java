package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.domain.redis.LoginAdmin;
import com.skhu.vote.model.Req.LoginReq;
import com.skhu.vote.model.Res.DefaultRes;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.repository.redis.LoginAdminRepository;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-02-02.
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LoginAdminRepository loginAdminRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public DefaultRes login(final LoginReq loginReq) {
        //SHA512EncryptUtils.encrypt)
        DefaultRes response = new DefaultRes();
        ADMIN admin = getAdmin(loginReq);
        if(admin == null) response.setMsg("잘못된 아이디/비밀번호 입니다.");
        else {
            if(isLogin(admin)) response.setMsg("이미 로그인 중입니다.");
            else {
                LoginAdmin loginAdmin = createUser(admin);
                saveEmc(loginAdmin);
                response.setStatus(StatusEnum.SUCCESS);
                response.setData(makeJwt(loginAdmin));
                response.setMsg("로그인 성공");
            }
        }
        return response;
    }

    @Override
    public void logout(final String id) {
        LoginAdmin loginAdmin = loginAdminRepository.findById(id);
        if(loginAdmin != null) {
            loginAdminRepository.delete(loginAdmin);
        }
        jwtService.logoutJwt();
    }

    private String makeJwt(final LoginAdmin loginAdmin) {
        return jwtService.createToken(loginAdmin, "emc");
    }

    private ADMIN getAdmin(final LoginReq loginReq) {
        return adminRepository.findByIdAndPassword(loginReq.getId(), loginReq.getPassword());
    }

    private void saveEmc(final LoginAdmin loginAdmin) {
        loginAdminRepository.save(loginAdmin);
    }

    private boolean isLogin(final ADMIN admin) {
        LoginAdmin loginAdmin = loginAdminRepository.findById(admin.getId());
        if(loginAdmin == null) return false;
        return true;
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

}
