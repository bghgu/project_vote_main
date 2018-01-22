package com.skhu.vote.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ds on 2018-01-23.
 */
@Data
@Entity
public class VOTE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;
    private String voteName;
    private Date startTime;
    private Date endTime;
    private int target;
}
