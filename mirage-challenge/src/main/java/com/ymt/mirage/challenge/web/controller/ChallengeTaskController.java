/**
 * 
 */
package com.ymt.mirage.challenge.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.challenge.dto.ChallengeTaskInfo;
import com.ymt.mirage.challenge.service.ChallengeTaskService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class ChallengeTaskController {
	
	@Autowired
	private ChallengeTaskService challengeTaskService;

	@RequestMapping(value = "/challengeTask", method = RequestMethod.POST)
	public ChallengeTaskInfo create(@RequestBody ChallengeTaskInfo challengeTaskInfo) {
		return challengeTaskService.create(challengeTaskInfo);
	}

	@RequestMapping(value = "/challengeTask", method = RequestMethod.GET)
	public Page<ChallengeTaskInfo> query(ChallengeTaskInfo challengeTaskInfo, Pageable pageable) {
		return challengeTaskService.query(challengeTaskInfo, pageable);
	}

	@RequestMapping(value = "/challengeTask/{id}", method = RequestMethod.GET)
	public ChallengeTaskInfo getInfo(@PathVariable Long id) {
		return challengeTaskService.getInfo(id);
	}
	
	@RequestMapping(value = "/challenge/{id}/task", method = RequestMethod.GET)
	public List<ChallengeTaskInfo> findByChallenge(@PathVariable Long id) {
		return challengeTaskService.findByChallenge(id);
	}

	@RequestMapping(value = "/challengeTask/{id}", method = RequestMethod.PUT)
	public ChallengeTaskInfo update(@RequestBody ChallengeTaskInfo challengeTaskInfo) {
		return challengeTaskService.update(challengeTaskInfo);
	}

	@RequestMapping(value = "/challengeTask/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		challengeTaskService.delete(id);
	}
}
