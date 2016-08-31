/**
 * 
 */
package com.ymt.mirage.challenge.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.challenge.domain.Challenge;
import com.ymt.mirage.challenge.domain.ChallengeTask;
import com.ymt.mirage.challenge.dto.ChallengeTaskInfo;
import com.ymt.mirage.challenge.repository.ChallengeRepository;
import com.ymt.mirage.challenge.repository.ChallengeTaskRepository;
import com.ymt.mirage.challenge.repository.spec.ChallengeTaskSpec;
import com.ymt.mirage.challenge.service.ChallengeTaskService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Service("challengeTaskService")
@Transactional
public class ChallengeTaskServiceImpl implements ChallengeTaskService {
	
	@Autowired
	private ChallengeTaskRepository challengeTaskRepository;
	
	@Autowired
	private ChallengeRepository challengeRepository;
	
	@Override
	public Page<ChallengeTaskInfo> query(ChallengeTaskInfo challengeTaskInfo, Pageable pageable) {
		Page<ChallengeTask> pageData = challengeTaskRepository.findAll(new ChallengeTaskSpec(challengeTaskInfo), pageable);
		return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<ChallengeTask, ChallengeTaskInfo>() {
			@Override
			protected void doConvert(ChallengeTask challengeTask, ChallengeTaskInfo info) {
				setChallengeInfo(challengeTask, info);
			}
		});
	}

	@Override
	public ChallengeTaskInfo create(ChallengeTaskInfo challengeTaskInfo) {
		ChallengeTask challengeTask = new ChallengeTask();
		setChallengeTaskInfo(challengeTaskInfo, challengeTask);
		Challenge challenge = challengeTask.getChallenge();
		challenge.setTaskCount(challenge.getTaskCount() + 1);
		challengeTaskInfo.setId(challengeTask.getId());
		return challengeTaskInfo;
	}

	private void setChallengeTaskInfo(ChallengeTaskInfo challengeTaskInfo, ChallengeTask challengeTask) {
		BeanUtils.copyProperties(challengeTaskInfo, challengeTask);
		Challenge challenge = challengeRepository.findOne(challengeTaskInfo.getChallengeId());
		if(challenge == null) {
			throw new PzException("挑战是必填项");
		}
		challengeTask.setChallenge(challenge);
		challengeTaskRepository.save(challengeTask);
	}

	@Override
	public ChallengeTaskInfo getInfo(Long id) {
		ChallengeTask challengeTask = challengeTaskRepository.findOne(id);
		ChallengeTaskInfo info = new ChallengeTaskInfo();
		BeanUtils.copyProperties(challengeTask, info);
		setChallengeInfo(challengeTask, info);
		return info;
	}

	@Override
	public ChallengeTaskInfo update(ChallengeTaskInfo challengeTaskInfo) {
		ChallengeTask challengeTask = challengeTaskRepository.findOne(challengeTaskInfo.getId());
		setChallengeTaskInfo(challengeTaskInfo, challengeTask);
		return challengeTaskInfo;
	}

	@Override
	public void delete(Long id) {
		ChallengeTask challengeTask = challengeTaskRepository.findOne(id);
		challengeTask.getChallenge().setTaskCount(challengeTask.getChallenge().getTaskCount() - 1);
		challengeTaskRepository.delete(challengeTask);		
	}

	private void setChallengeInfo(ChallengeTask challengeTask, ChallengeTaskInfo info) {
		info.setChallengeId(challengeTask.getChallenge().getId());
		info.setChallengeName(challengeTask.getChallenge().getName());
	}

	@Override
	public List<ChallengeTaskInfo> findByChallenge(Long id) {
		List<ChallengeTask> tasks = challengeTaskRepository.findByChallengeId(id, new Sort(Direction.ASC, "index"));
		return QueryResultConverter.convert(tasks, ChallengeTaskInfo.class);
	}

}
