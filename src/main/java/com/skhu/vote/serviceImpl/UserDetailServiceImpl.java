/*
package com.skhu.vote.serviceImpl;

import com.skhu.vote.entity.USER;
import com.skhu.vote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;


*/
/**
 * Created by ds on 2018-01-29.
 *//*


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        USER user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UsernameNotFound [" + username + "]");
        }
        LoginUser loginUser = createUser(user);
        return loginUser;
    }

    private LoginUser createUser(USER user) {
        LoginUser loginUser = new LoginUser(user);
        if (loginUser.getUserType().equals("1")) {
            loginUser.setRoles(Arrays.asList("ROLE_ADMIN"));
        } else {
            loginUser.setRoles(Arrays.asList("ROLE_USER"));
        }
        return loginUser;
    }


}
*/
