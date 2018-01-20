package com.skhu.vote.service;

import com.skhu.vote.entity.USER;

/**
 * Created by ds on 2018-01-20.
 */
public interface UserService {
    USER findById(int id);
}
