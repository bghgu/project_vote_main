package com.skhu.vote.serviceImpl;

import com.skhu.vote.repository.UserRepository;
import com.skhu.vote.service.ConfirmService;
import com.skhu.vote.utils.AuthCodeUtils;
import com.skhu.vote.utils.CodeQueue;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-01-23.
 */
@Service
public class ConfirmServiceImpl implements ConfirmService{

    @Autowired
    UserRepository userRepository;

    @Override
    public JSONObject voterConfirmation(int id) {
        //마지막으로 다시 한번 확인?
        //유권자가 이미 투표를 한 경우 실패 반환?
        //유권자 확인 표시
        userRepository.updateCheck(id);
        //학과 코드 반환
        int deptCode = userRepository.findById(id).getDepartment().getDepartmentId();
        //4자리 코드 생성
        int code4 = AuthCodeUtils.createCode();
        //6자리 코드 생성
        String code = String.valueOf(deptCode).concat(String.valueOf(code4));
        JSONObject jsonObject = new JSONObject();
        if(CodeQueue.insertCode(code)) {
            jsonObject.put("authCode", code);
            jsonObject.put("message", "SUCCESS");
        }else {
            jsonObject.put("message", "FAIL");
            jsonObject.put("mes", "잠시후 다시 시도해 주세요.");
        }
        return jsonObject;
    }
}
