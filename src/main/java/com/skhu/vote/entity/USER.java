package com.skhu.vote.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ds on 2018-01-20.
 */
@Data
@Entity
public class USER {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int userType;
    private String tel;
    private int check;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    DEPARTMENT department;
}
