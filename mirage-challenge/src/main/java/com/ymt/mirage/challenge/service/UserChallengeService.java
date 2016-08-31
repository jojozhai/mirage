/**
 * 
 */
package com.ymt.mirage.challenge.service;

import com.ymt.mirage.challenge.dto.Remind;
import com.ymt.mirage.challenge.dto.UserChallengeInfo;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
public interface UserChallengeService {

	UserChallengeInfo create(UserChallengeInfo userChallengeInfo) throws Exception;

	UserChallengeInfo getInfo(Long id);

	void updatePrivacy(Long id);

	void updateRemind(Long id, Remind remind);

}
