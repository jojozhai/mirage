/**
 * 
 */
package com.ymt.mirage.vote.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.vote.domain.VoteActivity;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
@Repository
public interface VoteActivityRepository extends PzRepository<VoteActivity> {

}
