/**
 * 
 */
package com.ymt.mirage.challenge.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.challenge.domain.Challenge;
import com.ymt.mirage.challenge.domain.Teacher;
import com.ymt.mirage.challenge.dto.ChallengeInfo;
import com.ymt.mirage.challenge.repository.ChallengeRepository;
import com.ymt.mirage.challenge.repository.TeacherRepository;
import com.ymt.mirage.challenge.repository.spec.ChallengeSpec;
import com.ymt.mirage.challenge.service.ChallengeService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Service("challengeService")
@Transactional
public class ChallengeServiceImpl implements ChallengeService {
	
	@Autowired
	private ChallengeRepository challengeRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public Page<ChallengeInfo> query(ChallengeInfo challengeInfo, Pageable pageable) {
		Page<Challenge> pageData = challengeRepository.findAll(new ChallengeSpec(challengeInfo), pageable);
		return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Challenge, ChallengeInfo>() {
			@Override
			protected void doConvert(Challenge challenge, ChallengeInfo info) {
				setTeacherInfo(challenge, info);
			}
		});
	}

	@Override
	public ChallengeInfo create(ChallengeInfo challengeInfo) {
		Challenge challenge = new Challenge();
		setChallengeInfo(challengeInfo, challenge);
		challengeInfo.setId(challenge.getId());
		return challengeInfo;
	}

	private void setChallengeInfo(ChallengeInfo challengeInfo, Challenge challenge) {
		BeanUtils.copyProperties(challengeInfo, challenge);
		Teacher teacher = teacherRepository.findOne(challengeInfo.getTeacherId());
		if(teacher == null) {
			throw new PzException("教师是必填项");
		}
		challenge.setTeacher(teacher);
		challengeRepository.save(challenge);
	}

	@Override
	public ChallengeInfo getInfo(Long id) {
		Challenge challenge = challengeRepository.findOne(id);
		ChallengeInfo info = new ChallengeInfo();
		BeanUtils.copyProperties(challenge, info);
		setTeacherInfo(challenge, info);
		return info;
	}

	@Override
	public ChallengeInfo update(ChallengeInfo challengeInfo) {
		Challenge challenge = challengeRepository.findOne(challengeInfo.getId());
		setChallengeInfo(challengeInfo, challenge);
		return challengeInfo;
	}

	@Override
	public void delete(Long id) {
		challengeRepository.delete(id);		
	}

	@Override
	public List<ChallengeInfo> findAll() {
		List<Challenge> challenges = challengeRepository.findAll();
		return QueryResultConverter.convert(challenges, ChallengeInfo.class);
	}

	private void setTeacherInfo(Challenge challenge, ChallengeInfo info) {
		info.setTeacherId(challenge.getTeacher().getId());
		info.setTeacherName(challenge.getTeacher().getName());
		info.setTeacherDesc(challenge.getTeacher().getDesc());
		info.setTeacherImage(challenge.getTeacher().getImage());
	}

}
