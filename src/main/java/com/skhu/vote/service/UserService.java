package com.skhu.vote.service;

import org.json.simple.JSONObject;

/**
 * Created by ds on 2018-01-20.
 */
public interface UserService {
    JSONObject checkId(int id);
    JSONObject confirm(int id);
}
