package com.skhu.vote.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ds on 2018-01-20.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = "department")
@ToString(exclude = "department")
public class USER {

    @Id
    private String id;
    private String name;
    private int userType;
    private String tel;
    private int voteCheck;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    DEPARTMENT department;
}
