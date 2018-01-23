package com.skhu.vote.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ds on 2018-01-23.
 */
@Data
@Entity
@ToString(exclude = "candidates")
@EqualsAndHashCode(exclude = "candidates")
public class VOTE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;
    private String voteName;
    private Date startTime;
    private Date endTime;
    private int target;

    @OneToMany(mappedBy = "vote")
    List<CANDIDATE> candidates;
}
