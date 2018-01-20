package com.skhu.vote.serviceImpl;

import com.skhu.vote.entity.USER;
import com.skhu.vote.repository.UserRepository;
import com.skhu.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-01-20.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public USER findById(int id) {
        return userRepository.findById(id);
    }
}
