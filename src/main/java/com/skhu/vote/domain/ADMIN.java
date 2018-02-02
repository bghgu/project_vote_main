package com.skhu.vote.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ds on 2018-02-02.
 */

@Data
@Entity
public class ADMIN {

    @Id
    private String id;
    private String name;
    private String password;
    private String departmentName;
    private int type;

}
