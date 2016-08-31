/**
 * 
 */
package com.ymt.mirage.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.challenge.domain.Participator;
import com.ymt.mirage.challenge.domain.UserChallenge;
import com.ymt.mirage.challenge.dto.ParticipatorInfo;
import com.ymt.mirage.challenge.dto.ParticipatorType;
import com.ymt.mirage.challenge.repository.ParticipatorRepository;
import com.ymt.mirage.challenge.repository.UserChallengeRepository;
import com.ymt.mirage.challenge.service.ParticipatorService;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
@Service("participatorService")
@Transactional
public class ParticipatorServiceImpl implements ParticipatorService {
	
	@Autowired
	private ParticipatorRepository participatorRepository;
	
	@Autowired
	private UserChallengeRepository userChallengeRepository;

	/* (non-Javadoc)
	 * @see com.ymt.mirage.challenge.service.ParticipatorService#getInfo(java.lang.Long, java.lang.Long)
	 */
	@Override
	public ParticipatorInfo getInfo(Long userChallengeId, Long currentUserId) {
		if(currentUserId == null) {
			return null;
		}else{
			ParticipatorInfo info = new ParticipatorInfo();
			Participator participator = participatorRepository.findByChallengeIdAndUserId(userChallengeId, currentUserId);
			if(participator == null) {
				UserChallenge userChallenge = userChallengeRepository.findOne(userChallengeId);
				if(userChallenge.getUser().getId().equals(currentUserId)) {
					info.setType(ParticipatorType.CREATER);
				}else{
					info.setType(ParticipatorType.NONE);
				}
			}else{
				if(participator.getChallenge().getUser().getId().equals(currentUserId)) {
					info.setType(ParticipatorType.CREATER);
				}else{
					info.setType(participator.getType());
				}
			}
			return info;
		}
	}

}
