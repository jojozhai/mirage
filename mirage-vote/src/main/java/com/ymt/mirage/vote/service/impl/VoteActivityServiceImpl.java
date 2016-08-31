/**
 * 
 */
package com.ymt.mirage.vote.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.vote.domain.VoteActivity;
import com.ymt.mirage.vote.dto.VoteActivityInfo;
import com.ymt.mirage.vote.repository.VoteActivityRepository;
import com.ymt.mirage.vote.service.VoteActivityService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
@Service("voteActivityService")
@Transactional
public class VoteActivityServiceImpl implements VoteActivityService {
	
	@Autowired
	private VoteActivityRepository voteActivityRepository;

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.VoteActivityAdminService#create(com.ymt.pz365.message.dto.VoteActivityInfo)
	 */
	@Override
	public VoteActivityInfo create(VoteActivityInfo voteActivityInfo) {
		VoteActivity voteActivity = new VoteActivity();
		BeanUtils.copyProperties(voteActivityInfo, voteActivity);
		voteActivityRepository.save(voteActivity);
		return getInfo(voteActivity.getId());
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.VoteActivityAdminService#query(com.ymt.pz365.message.dto.VoteActivityInfo, org.springframework.data.domain.Pageable)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<VoteActivityInfo> query(VoteActivityInfo condition, Pageable pageable) {
		Page<VoteActivity> pageData = voteActivityRepository.findAll(pageable);
		return QueryResultConverter.convert(pageData, VoteActivityInfo.class, pageable);
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.VoteActivityAdminService#getInfo(java.lang.Long)
	 */
	@Override
	public VoteActivityInfo getInfo(Long id) {
		VoteActivityInfo voteActivityInfo = new VoteActivityInfo();
		VoteActivity voteActivity = voteActivityRepository.findOne(id);
		if(voteActivity != null) {
			BeanUtils.copyProperties(voteActivity, voteActivityInfo);
			voteActivity.setBrowseCount(voteActivity.getBrowseCount() + 1);
			voteActivityRepository.save(voteActivity);
		}
		return voteActivityInfo;
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.VoteActivityAdminService#update(com.ymt.pz365.message.dto.VoteActivityInfo)
	 */
	@Override
	public VoteActivityInfo update(VoteActivityInfo voteActivityInfo) {
		VoteActivity voteActivity = voteActivityRepository.findOne(voteActivityInfo.getId());
		BeanUtils.copyProperties(voteActivityInfo, voteActivity);
		voteActivityRepository.save(voteActivity);
		return voteActivityInfo;
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.VoteActivityAdminService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		voteActivityRepository.delete(id);
	}

}
