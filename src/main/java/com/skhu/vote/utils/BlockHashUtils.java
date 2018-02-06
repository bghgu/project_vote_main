package com.skhu.vote.utils;

import com.skhu.vote.model.BlockBody;
import com.skhu.vote.model.BlockHeader;
import com.skhu.vote.model.Req.CandidateReq;

import java.util.Date;

/**
 * Created by ds on 2018-02-06.
 */
public class BlockHashUtils {

    public static BlockBody createBlockHash() {
        CandidateReq candidateReq = new CandidateReq();
        candidateReq.setCandidateId(0);
        candidateReq.setVoteId(0);
        BlockBody blockBody = new BlockBody(candidateReq, "00000000");
        System.out.println(blockBody.hashCode());
        return blockBody;
    }
    static Date date = new Date();
    public static void main(String args[]) {
        BlockHeader blockHeader = new BlockHeader(createBlockHash(), "이전 노드의 블록 해쉬값");
        System.out.println(blockHeader.toString());
        System.out.println("현재 노드의 블록 해쉬 값은 : " + blockHeader.getMerkleHash());
    }
}
