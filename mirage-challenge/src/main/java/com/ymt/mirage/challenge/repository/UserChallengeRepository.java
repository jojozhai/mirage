/**
 * 
 */
package com.ymt.mirage.challenge.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.challenge.domain.UserChallenge;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Repository
public interface UserChallengeRepository extends PzRepository<UserChallenge> {

	UserChallenge findByChallengeIdAndUserId(Long challengeId, Long userId);

}
