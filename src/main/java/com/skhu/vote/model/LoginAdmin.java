package com.skhu.vote.model;

import com.skhu.vote.domain.ADMIN;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ds on 2018-01-31.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("loginAdmin")
public class LoginAdmin implements Serializable {

    private static final long serialVersionUID = 2998098504408887444L;

    @Id
    private @Indexed
    String id;

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
