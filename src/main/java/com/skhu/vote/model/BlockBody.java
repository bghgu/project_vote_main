package com.skhu.vote.model;

import com.skhu.vote.model.Req.CandidateReq;
import lombok.*;

import java.util.Date;
import java.util.Objects;

/**
 * Created by ds on 2018-02-06.
 */

@Getter
public class BlockBody {
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

    @Override
    public String toString() {
        return "BlockBody{" +
                "voteTime=" + voteTime +
                ", candidateId=" + candidateId +
                ", voteId=" + voteId +
                ", authCode='" + authCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockBody blockBody = (BlockBody) o;
        return candidateId == blockBody.candidateId &&
                voteId == blockBody.voteId &&
                Objects.equals(voteTime, blockBody.voteTime) &&
                Objects.equals(authCode, blockBody.authCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteTime, candidateId, voteId, authCode);
    }
}
