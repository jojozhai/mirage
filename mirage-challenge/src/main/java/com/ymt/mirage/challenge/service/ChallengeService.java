/**
 * 
 */
package com.ymt.mirage.challenge.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.challenge.dto.ChallengeInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface ChallengeService {

	Page<ChallengeInfo> query(ChallengeInfo challengeInfo, Pageable pageable);
	
	ChallengeInfo create(ChallengeInfo challengeInfo);

	ChallengeInfo getInfo(Long id);

	ChallengeInfo update(ChallengeInfo challengeInfo);

	void delete(Long id);

	List<ChallengeInfo> findAll();

}
