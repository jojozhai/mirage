/**
 * 
 */
package com.ymt.mirage.challenge.service;

import com.ymt.mirage.challenge.dto.ParticipatorInfo;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
public interface ParticipatorService {

	ParticipatorInfo getInfo(Long userChallengeId, Long currentUserId);

}
