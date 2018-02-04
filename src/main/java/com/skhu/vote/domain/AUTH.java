package com.skhu.vote.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ds on 2018-01-30.
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AUTH {
    @Id
    private String authCode;
    private int departmentId;
    private int loginCheck;
    private int voteCheck;
    private Date loginTime;

    public AUTH(final String authCode, final int departmentId) {
        this.authCode = authCode;
        this.departmentId = departmentId;
    }

}
