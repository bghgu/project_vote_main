package com.skhu.vote.repository.redis;

import com.skhu.vote.model.LoginAdmin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ds on 2018-02-20.
 */
public interface LoginAdminRepository extends CrudRepository<LoginAdmin, Integer> {
    LoginAdmin findById(final String id);
    void deleteById(final String id);
}
