package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.LoginAdmin;
import com.skhu.vote.model.Req.LoginReq;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.repository.redis.LoginAdminRepository;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ds on 2018-02-02.
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LoginAdminRepository loginAdminRepository;

    @Override
    public LoginAdmin login(final LoginReq loginReq) {
        //SHA512EncryptUtils.encrypt)
        ADMIN admin = adminRepository.findByIdAndPassword(loginReq.getId(), loginReq.getPassword());
        //아이디 비밀번호 틀림
        if(admin == null) {
            return null;
        }else {
            if(isLogin(admin.getId())) {
                LoginAdmin loginAdmin = createUser(admin);
                saveEmc(loginAdmin);
                return loginAdmin;
            }else {
                return null;
            }
        }
    }

    private void saveEmc(final LoginAdmin loginAdmin) {
        loginAdminRepository.save(loginAdmin);
    }

    /**
     * 로그인 여부 확인
     * @param id
     * @return
     */
    private boolean isLogin(final String id) {
        LoginAdmin loginAdmin = loginAdminRepository.findById(id);
        if(loginAdmin == null) {
            System.out.println("로그인 가능");
            return true;
        }else {
            System.out.println("로그인 중");
            return false;
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
    public void logout(final String id) {
        System.out.println(id);
        LoginAdmin loginAdmin = loginAdminRepository.findById(id);
        if(loginAdmin != null) {
            loginAdminRepository.delete(loginAdmin);
        }
    }

}
