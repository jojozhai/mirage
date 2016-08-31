/**
 * 
 */
package com.ymt.mirage.challenge.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.ymt.mirage.challenge.domain.ChallengeTask;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Repository
public interface ChallengeTaskRepository extends PzRepository<ChallengeTask> {

	List<ChallengeTask> findByChallengeId(Long id, Sort sort);

}
