package com.skhu.vote.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ds on 2018-02-04.
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BLOCKCHAIN {
    private String preBlockHash;
    private String merkleHash;
    private Date voteTime;
    @Id
    private String blockId;
    private int value;
    private int voteId;
    private String authCode;
}
