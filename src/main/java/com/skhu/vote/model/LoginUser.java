/*
package com.skhu.vote.model;

import com.skhu.vote.entity.USER;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

*/
/**
 * Created by ds on 2018-01-29.
 *//*


@Data
public class LoginUser implements UserDetails{

    private static final long serialVersionUID = -4412089620730782213L;
    private String id;
    private String name;
    private String password;
    private String departmentName;
    private int type;
    private List<String> roles;

    public LoginUser() {
    }

    public LoginUser(USER user) {
        this.id = user.getId();
        this.name =  user.getName();
        //this.password = user.getPassword();
        this.type = user.getUserType();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
*/
