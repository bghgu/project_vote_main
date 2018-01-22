package com.skhu.vote.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ds on 2018-01-23.
 */
@Data
@Entity
public class CANDIDATE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;
    private String name;
    private String departmentName;
    private String position;
    private String campName;
    private String photoUrl;
    private int voteCount;
    private int voteId;
}
