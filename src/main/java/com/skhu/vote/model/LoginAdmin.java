package com.skhu.vote.model;

import com.skhu.vote.domain.ADMIN;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ds on 2018-01-31.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginAdmin implements Serializable {

    private static final long serialVersionUID = 2998098504408887444L;

    private String id;
    private String name;
    private int type;
    private String roles;

    public LoginAdmin(final ADMIN admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.type = admin.getType();
    }

    public void setRoles(final String roles) {
        this.roles = roles;
    }
}
