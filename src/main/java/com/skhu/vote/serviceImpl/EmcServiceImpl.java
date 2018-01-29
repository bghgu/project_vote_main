package com.skhu.vote.serviceImpl;

import com.skhu.vote.domain.USER;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.UserRepository;
import com.skhu.vote.service.EmcService;
import com.skhu.vote.utils.AuthCodeUtils;
import com.skhu.vote.utils.CodeQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-01-30.
 */
@Service
public class EmcServiceImpl implements EmcService {

    @Autowired
    UserRepository userRepository;

    @Override
    public DefaultResponse checkId(final String id) {
        USER user = userRepository.findById(id);
        DefaultResponse response = new DefaultResponse();
        if(user != null) {
            if(user.getUserType() == 0) {
                response.setMsg("유권자가 아닙니다.");
            }else {
                if(user.getVoteCheck() == 0) {
                    response.setStatus(StatusEnum.SUCCESS);
                    response.setData(user);
                    response.setMsg("인증번호 발급 가능");
                }else {
                    response.setMsg("이미 투표를 진행했습니다.");
                }
            }
        }else {
            response.setMsg("등록된 학생이 없습니다.");
        }
        return response;
    }

    @Override
    public DefaultResponse voterConfirmation(final String id) {
        //마지막으로 다시 한번 확인?
        //유권자가 이미 투표를 한 경우 실패 반환?
        //유권자 확인 표시
        userRepository.updateCheck(id);
        DefaultResponse response = new DefaultResponse();
        //학과 코드 반환
        int deptCode = userRepository.findById(id).getDepartment().getDepartmentId();
        //4자리 코드 생성
        int code4 = AuthCodeUtils.createCode();
        //6자리 코드 생성
        String code = String.valueOf(deptCode).concat(String.valueOf(code4));
        if(CodeQueue.insertCode(code)) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(code);
            response.setMsg("인증번호 발급 성공");
        }else {
            response.setMsg("인증번호 발급 실패");
        }
        return response;
    }

}
