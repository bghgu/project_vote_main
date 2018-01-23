package com.skhu.vote.service;

import com.skhu.vote.entity.VOTE;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */

public interface VoteService {
    JSONObject voteList(int deptId);
}
