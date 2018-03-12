package com.skhu.vote.model;

import com.skhu.vote.utils.SHA512EncryptUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by ds on 2018-02-04.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("block")
public class BlockHeader implements Serializable {

    private static final long serialVersionUID = -597819263112668252L;

    //이전블록 해쉬값
    private String preBlockHash;
    //현재 블록 해쉬값 = 리스트의 키 값
    private String merkleHash;
    //블록 생성 시간
    private Date createBlockTime;
    //현재 블록 바디 해쉬값
    private String blockHash;
    //블록 바디 값
    private BlockBody blockBody;

    public BlockHeader(final BlockBody blockBody, final String preBlockHash) {
        this.preBlockHash = preBlockHash;
        this.blockHash = blockBody.hash();
        this.blockBody = blockBody;
        this.createBlockTime = new Date();
        this.setMerkleHash();
    }

    private void setMerkleHash() {
        if(merkleHash == null)
            this.merkleHash = SHA512EncryptUtils.encrypt(blockHash);
    }

}
