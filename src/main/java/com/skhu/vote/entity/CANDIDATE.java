package com.skhu.vote.entity;

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
@ToString(exclude = "vote")
@EqualsAndHashCode(exclude = "vote")
public class CANDIDATE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;
    private String name;
    private String departmentName;
    private String position;
    private String campName;
    private String photoUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "voteId")
    VOTE vote;
}
