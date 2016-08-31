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

import com.ymt.mirage.challenge.dto.ChallengeInfo;
import com.ymt.mirage.challenge.service.ChallengeService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class ChallengeController {
	
	@Autowired
	private ChallengeService challengeService;

	@RequestMapping(value = "/challenge", method = RequestMethod.POST)
	public ChallengeInfo create(@RequestBody ChallengeInfo challengeInfo) {
		return challengeService.create(challengeInfo);
	}

	@RequestMapping(value = "/challenge", method = RequestMethod.GET)
	public Page<ChallengeInfo> query(ChallengeInfo challengeInfo, Pageable pageable) {
		return challengeService.query(challengeInfo, pageable);
	}
	
	@RequestMapping(value = "/challenge/all", method = RequestMethod.GET)
	public List<ChallengeInfo> findAll() {
		return challengeService.findAll();
	}

	@RequestMapping(value = "/challenge/{id}", method = RequestMethod.GET)
	public ChallengeInfo getInfo(@PathVariable Long id) {
		return challengeService.getInfo(id);
	}

	@RequestMapping(value = "/challenge/{id}", method = RequestMethod.PUT)
	public ChallengeInfo update(@RequestBody ChallengeInfo challengeInfo) {
		return challengeService.update(challengeInfo);
	}

	@RequestMapping(value = "/challenge/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		challengeService.delete(id);
	}
}
