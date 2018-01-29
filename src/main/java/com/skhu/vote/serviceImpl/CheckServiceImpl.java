package com.skhu.vote.serviceImpl;

/**
 * Created by ds on 2018-01-23.
 */

import com.skhu.vote.entity.USER;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.Status;
import com.skhu.vote.repository.UserRepository;
import com.skhu.vote.service.CheckService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl implements CheckService{

    @Autowired
    UserRepository userRepository;

    @Override
    public DefaultResponse checkId(String id) {
        USER user = userRepository.findById(id);
        DefaultResponse response = new DefaultResponse();
        if(user != null) {
            if(user.getUserType() == 0) {
                response.setMsg("유권자가 아닙니다.");
            }else {
                if(user.getVoteCheck() == 0) {
                    response.setStatus(Status.SUCCESS);
                    response.setData(user);
                    response.setMsg("인증번호 발급 가능");
                }else {
                    response.setMsg("이미 투표를 진행했습니다.");
                }
            }
            return response;
        }else {
            response.setMsg("등록된 학생이 없습니다.");
            return response;
        }
    }
}
