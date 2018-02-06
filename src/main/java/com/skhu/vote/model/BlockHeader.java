package com.skhu.vote.model;

import com.skhu.vote.utils.SHA512EncryptUtils;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by ds on 2018-02-04.
 */

@Getter
public class BlockHeader implements Serializable{
    //이전블록 해쉬값
    private String preBlockHash;
    //현재 블록 해쉬값 = 리스트의 키 값
    private String merkleHash;
    //블록 생성 시간
    private Date createBlockTime;
    //현재 블록 바디 해쉬값
    private int blockHash;
    //블록 바디 값
    private BlockBody blockBody;

    public BlockHeader(final BlockBody blockBody, final String preBlockHash) {
        this.preBlockHash = preBlockHash;
        this.createBlockTime = new Date();
        this.blockHash = blockBody.hashCode();
        this.blockBody = blockBody;
        this.setMerkleHash();
    }

    private void setMerkleHash() {
        if(merkleHash == null)
            this.merkleHash = SHA512EncryptUtils.encrypt(String.valueOf(this.hashCode()));
    }

    @Override
    public String toString() {
        return "BlockHeader{" +
                "preBlockHash='" + preBlockHash + '\'' +
                ", merkleHash='" + merkleHash + '\'' +
                ", createBlockTime=" + createBlockTime +
                ", blockHash=" + blockHash +
                ", blockBody=" + blockBody +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockHeader that = (BlockHeader) o;
        return blockHash == that.blockHash &&
                Objects.equals(preBlockHash, that.preBlockHash) &&
                Objects.equals(merkleHash, that.merkleHash) &&
                Objects.equals(createBlockTime, that.createBlockTime) &&
                Objects.equals(blockBody, that.blockBody);
    }

    @Override
    public int hashCode() {

        return Objects.hash(preBlockHash, merkleHash, createBlockTime, blockHash, blockBody);
    }
}
