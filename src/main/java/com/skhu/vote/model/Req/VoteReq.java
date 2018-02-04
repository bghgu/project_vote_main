package com.skhu.vote.model.Req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by ds on 2018-02-04.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoteReq {
    private String code;
    private List<CandidateReq> voteList;
}
