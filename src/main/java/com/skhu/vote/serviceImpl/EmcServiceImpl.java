package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.AUTH;
import com.skhu.vote.domain.USER;
import com.skhu.vote.model.Res.DefaultRes;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AuthRepository;
import com.skhu.vote.repository.UserRepository;
import com.skhu.vote.service.EmcService;
import com.skhu.vote.utils.AuthCodeUtils;

import com.skhu.vote.utils.voterCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ds on 2018-01-30.
 */

@Service
public class EmcServiceImpl implements EmcService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthRepository authRepository;

    @Override
    public DefaultRes getUser(final String id) {
        USER user = userRepository.findById(id);
        DefaultRes response = voterCheckUtils.check(user);
        if(response.getMsg() == null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(user);
            response.setMsg("인증번호 발급 가능");
        }
        return response;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DefaultRes saveAuth(final String id) {
        USER user = userRepository.findById(id);
        DefaultRes response = voterCheckUtils.check(user);
        if(response.getMsg() == null) {
            //학과 코드 반환
            int departmentId = user.getDepartment().getDepartmentId();
            //코드 생성
            String code = AuthCodeUtils.createStringCode();
            //이미 중복 인증코드가 있는 경우?
            AUTH test = authRepository.findByAuthCode(code);
            if (test != null) response.setMsg("인증코드가 중복됩니다. 잠시후 다시 시도해 주세요.");
            else {
                int before = authRepository.findAll().size();
                //인증코드 DB 저장
                authRepository.save(new AUTH(code, departmentId));
                int after = authRepository.findAll().size();
                if(before == after) response.setMsg("인증번호 저장 실패. 잠시후 다시 시도해 주세요.");
                else {
                    //유권자 확인 표시
                    userRepository.updateCheck(id);
                    response.setStatus(StatusEnum.SUCCESS);
                    response.setData(code);
                    response.setMsg("인증번호 발급 성공");
                }
            }
        }
        return response;
    }
}
