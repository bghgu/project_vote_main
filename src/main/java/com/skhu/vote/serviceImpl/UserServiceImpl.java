package com.skhu.vote.serviceImpl;

import com.skhu.vote.entity.USER;
import com.skhu.vote.repository.UserRepository;
import com.skhu.vote.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-01-20.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public JSONObject checkId(int id) {
        USER user = userRepository.findById(id);
        JSONObject jsonObject = new JSONObject();
        if(user != null) {
            if(user.getUserType() == 0) {
                jsonObject.put("data", "유권자가 아닙니다.");
                jsonObject.put("message", "FAIL");
            }else {
                if(user.getCheck() == 0) {
                    jsonObject.put("data", user);
                    jsonObject.put("message", "SUCCESS");
                }else {
                    jsonObject.put("data", "이미 투표를 진행했습니다.");
                    jsonObject.put("message", "FAIL");
                }
            }
            return jsonObject;
        }else {
            jsonObject.put("data", "등록된 학생이 없습니다.");
            jsonObject.put("message", "FAIL");
            return jsonObject;
        }
    }

    @Override
    public JSONObject confirm(int id) {
        System.out.println(id);
        userRepository.updateCheck(id);
        return null;
    }
}
