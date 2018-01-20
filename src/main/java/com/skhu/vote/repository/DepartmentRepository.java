package com.skhu.vote.repository;

import com.skhu.vote.entity.DEPARTMENT;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ds on 2018-01-20.
 */
public interface DepartmentRepository extends JpaRepository<DEPARTMENT, Integer> {

}
