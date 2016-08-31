/**
 * 
 */
package com.ymt.mirage.apply.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.apply.domain.Apply;
import com.ymt.mirage.apply.dto.ApplyInfo;
import com.ymt.mirage.apply.repository.ApplyRepository;
import com.ymt.mirage.apply.repository.spec.ApplySpec;
import com.ymt.mirage.apply.service.ApplyService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@Service("applyService")
@Transactional
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private ApplyRepository applyRepository;
	
	@Override
	public Page<ApplyInfo> query(ApplyInfo applyInfo, Pageable pageable) {
		Page<Apply> pageData = applyRepository.findAll(new ApplySpec(applyInfo), pageable);
		return QueryResultConverter.convert(pageData, ApplyInfo.class, pageable);
	}

	@Override
	public ApplyInfo create(ApplyInfo applyInfo) {
		Apply apply = new Apply();
		BeanUtils.copyProperties(applyInfo, apply);
		applyInfo.setId(applyRepository.save(apply).getId());
		return applyInfo;
	}

	@Override
	public ApplyInfo getInfo(Long id) {
		Apply apply = applyRepository.findOne(id);
		ApplyInfo info = new ApplyInfo();
		BeanUtils.copyProperties(apply, info);
		return info;
	}

	@Override
	public ApplyInfo update(ApplyInfo applyInfo) {
		Apply apply = applyRepository.findOne(applyInfo.getId());
		BeanUtils.copyProperties(applyInfo, apply);
		applyRepository.save(apply);
		return applyInfo;
	}

	@Override
	public void delete(Long id) {
		applyRepository.delete(id);		
	}
	
}
