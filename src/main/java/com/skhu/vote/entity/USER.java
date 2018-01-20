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
public class USER implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int userType;
    private String tel;
    int check;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    DEPARTMENT department;
}
