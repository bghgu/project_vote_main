package com.skhu.vote.repository;

import com.skhu.vote.domain.BLOCKCHAIN;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ds on 2018-02-04.
 */
public interface BlockChainRepository extends JpaRepository<BLOCKCHAIN, Integer> {
}
