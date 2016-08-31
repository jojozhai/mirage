/**
 * 
 */
package com.ymt.mirage.challenge.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.challenge.dto.Remind;
import com.ymt.mirage.challenge.dto.UserChallengeInfo;
import com.ymt.mirage.challenge.service.UserChallengeService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class UserChallengeController {
	
	@Autowired
	private UserChallengeService userChallengeService;

	@RequestMapping(value = "/userChallenge", method = RequestMethod.POST)
	public UserChallengeInfo create(@RequestBody UserChallengeInfo userChallengeInfo) throws Exception {
		userChallengeInfo.setUserId(CurrentUserHolder.getCurrentUserId());
		return userChallengeService.create(userChallengeInfo);
	}
	
	@RequestMapping(value = "/userChallenge/{id}", method = RequestMethod.GET)
	public UserChallengeInfo getInfo(@PathVariable Long id) {
		return userChallengeService.getInfo(id);
	}
	
	@RequestMapping(value = "/userChallenge/{id}/privacy", method = RequestMethod.PUT)
	public void updatePrivacy(@PathVariable Long id) {
		userChallengeService.updatePrivacy(id);
	}
	
	@RequestMapping(value = "/userChallenge/{id}/remind", method = RequestMethod.PUT)
	public void updateRemind(@PathVariable Long id, @RequestBody Remind remind) {
		userChallengeService.updateRemind(id, remind);
	}

}
