package com.skhu.vote.domain;

import com.skhu.vote.model.BlockHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ds on 2018-02-04.
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BLOCKCHAIN{

    //블록 헤더
    //이전블록 해쉬값
    private String preBlockHash;
    //현재 블록 해쉬값
    private String merkleHash;
    //블록 생성 시간
    private Date createBlockTime;
    //현재 블록 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blockId;
    //현재 블록 바디 해쉬값
    private String blockHash;

    //블록 바디
    //투표 시간
    private Date voteTime;
    //투표 값
    private int candidateId;
    //투표 종류
    private int voteId;
    //투표한 인증코드
    private String authCode;

    public BLOCKCHAIN(final BlockHeader blockHeader) {
        this.preBlockHash = blockHeader.getPreBlockHash();
        this.merkleHash = blockHeader.getMerkleHash();
        this.createBlockTime = blockHeader.getCreateBlockTime();
        this.blockHash = blockHeader.getBlockHash();
        this.voteTime = blockHeader.getBlockBody().getVoteTime();
        this.candidateId = blockHeader.getBlockBody().getCandidateId();
        this.voteId = blockHeader.getBlockBody().getVoteId();
        this.authCode = blockHeader.getBlockBody().getAuthCode();
    }
}
