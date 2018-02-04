package com.skhu.vote.model;

import com.skhu.vote.model.Req.CandidateReq;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by ds on 2018-02-04.
 */

@Getter
@NoArgsConstructor
public class Block {
    //이전블록 해쉬값
    private String preBlockHash;
    //현재 블록 해쉬값
    private String merkleHash;
    //투표 시간
    private Date voteTime;
    //현재 블록 고유 인덱스값
    private int blockId;
    //투표 값
    private int value;
    //투표 종류
    private int voteId;
    //투표한 인증코드
    private String authCode;

    public Block(final CandidateReq candidateReq, final String code) {
        this.voteTime = new Date();
        this.value = candidateReq.getCandidateId();
        this.voteId = candidateReq.getVoteId();
        this.authCode = code;
    }

}
