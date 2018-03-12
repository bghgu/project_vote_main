package com.skhu.vote.model;

import com.skhu.vote.domain.BLOCKCHAIN;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.utils.BlockHashUtils;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by ds on 2018-02-06.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockBody implements Serializable{

    private static final long serialVersionUID = -6156036100875586824L;

    //투표 시간
    private Date voteTime;
    //투표 값
    private int candidateId;
    //투표 종류
    private int voteId;
    //투표한 인증코드
    private String authCode;

    public BlockBody(final CandidateReq candidateReq, final String code) {
        this.voteTime = new Date();
        this.candidateId = candidateReq.getCandidateId();
        this.voteId = candidateReq.getVoteId();
        this.authCode = code;
    }

    public BlockBody(final BLOCKCHAIN blockchain) {
        this.voteTime = blockchain.getVoteTime();
        this.candidateId = blockchain.getCandidateId();
        this.voteId = blockchain.getVoteId();
        this.authCode = blockchain.getAuthCode();
    }

    public String hash() {
        return BlockHashUtils.hashCode(this.candidateId, this.voteId, this.authCode);
    }
}
