package com.skhu.vote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by ds on 2018-01-23.
 */
@Data
@Entity
@ToString(exclude = "voteInfo")
@EqualsAndHashCode(exclude = "voteInfo")
public class CANDIDATE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;
    private String campName;
    private String leaderName;
    private String leaderDeptName;
    private String subLeaderName;
    private String subLeaderDeptName;
    private String photo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "voteId")
    VOTEINFO voteInfo;
}
