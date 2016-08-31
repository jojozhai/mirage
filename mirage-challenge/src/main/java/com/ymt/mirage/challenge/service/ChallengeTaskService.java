/**
 * 
 */
package com.ymt.mirage.challenge.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.challenge.dto.ChallengeTaskInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface ChallengeTaskService {

	Page<ChallengeTaskInfo> query(ChallengeTaskInfo challengeTaskInfo, Pageable pageable);
	
	ChallengeTaskInfo create(ChallengeTaskInfo challengeTaskInfo);

	ChallengeTaskInfo getInfo(Long id);

	ChallengeTaskInfo update(ChallengeTaskInfo challengeTaskInfo);

	void delete(Long id);

	List<ChallengeTaskInfo> findByChallenge(Long id);

}
