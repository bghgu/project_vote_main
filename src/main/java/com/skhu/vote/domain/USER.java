package com.skhu.vote.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

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
    private int confirmCheck;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    DEPARTMENT department;
}
