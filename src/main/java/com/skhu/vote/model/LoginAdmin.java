package com.skhu.vote.model;

import com.skhu.vote.domain.ADMIN;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by ds on 2018-01-31.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginAdmin {

    private static final long serialVersionUID = 2998098504408887444L;

    private String id;
    private String name;
    private String password;
    private String departmentName;
    private int type;
    private List<String> roles;

    public LoginAdmin(final ADMIN admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.departmentName = admin.getDepartmentName();
        this.type = admin.getType();
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
