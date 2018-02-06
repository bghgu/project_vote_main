package com.skhu.vote.model;

import com.skhu.vote.model.Req.CandidateReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by ds on 2018-02-04.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block implements Serializable{



    //이전블록 해쉬값
    private String preBlockHash;
    //현재 블록 해쉬값
    private String merkleHash;
    //투표 시간
    private Date voteTime;
    //현재 블록 고유 인덱스값
    private String blockId;
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
