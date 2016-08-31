/**
 * 
 */
package com.ymt.mirage.vote.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ymt.mirage.vote.domain.Vote;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
@Repository
public interface VoteRepository extends PzRepository<Vote> {

	Vote findByEnrollIdAndUserId(Long enrollId, Long userId);

	Page<Vote> findByUserId(Long userId, Pageable pageable);

}
