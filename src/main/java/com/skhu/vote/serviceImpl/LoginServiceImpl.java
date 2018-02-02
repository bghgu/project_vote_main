package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.LoginAdmin;
import com.skhu.vote.model.LoginRequest;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.service.LoginService;
import com.skhu.vote.utils.SHA512EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by ds on 2018-02-02.
 */

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public LoginAdmin login(final LoginRequest loginRequest) {
        //SHA512EncryptUtils.encrypt)
        ADMIN admin = adminRepository.findByIdAndPassword(loginRequest.getId(), loginRequest.getPassword());
        System.out.println(admin.toString());
        if(admin == null) {
            System.out.println("null");
        }
        LoginAdmin loginAdmin = createUser(admin);
        return loginAdmin;
    }

    private LoginAdmin createUser(ADMIN admin) {
        LoginAdmin loginAdmin = new LoginAdmin(admin);
        if(loginAdmin.getType() == 2) {
            //선관위원장
            loginAdmin.setRoles(Arrays.asList("ROLE_MASTER"));
        }else {
            //선관위
            loginAdmin.setRoles(Arrays.asList("ROLE_ADMIN"));
        }
        return loginAdmin;
    }


}
