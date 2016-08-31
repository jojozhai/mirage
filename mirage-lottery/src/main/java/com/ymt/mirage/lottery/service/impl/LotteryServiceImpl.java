/**
 * 
 */
package com.ymt.mirage.lottery.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.lottery.domain.Lottery;
import com.ymt.mirage.lottery.dto.LotteryInfo;
import com.ymt.mirage.lottery.repository.LotteryRepository;
import com.ymt.mirage.lottery.repository.spec.LotterySpec;
import com.ymt.mirage.lottery.service.LotteryService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
@Service("lotteryService")
@Transactional
public class LotteryServiceImpl implements LotteryService {

	@Autowired
	private LotteryRepository lotteryRepository;
	
	@Override
	public Page<LotteryInfo> query(LotteryInfo lotteryInfo, Pageable pageable) {
		Page<Lottery> pageData = lotteryRepository.findAll(new LotterySpec(lotteryInfo), pageable);
		return QueryResultConverter.convert(pageData, LotteryInfo.class, pageable);
	}

	@Override
	public LotteryInfo create(LotteryInfo lotteryInfo) {
		Lottery lottery = new Lottery();
		BeanUtils.copyProperties(lotteryInfo, lottery);
		lotteryInfo.setId(lotteryRepository.save(lottery).getId());
		return lotteryInfo;
	}

	@Override
	public LotteryInfo getInfo(Long id) {
		Lottery lottery = lotteryRepository.findOne(id);
		LotteryInfo info = new LotteryInfo();
		BeanUtils.copyProperties(lottery, info);
		return info;
	}

	@Override
	public LotteryInfo update(LotteryInfo lotteryInfo) {
		Lottery lottery = lotteryRepository.findOne(lotteryInfo.getId());
		BeanUtils.copyProperties(lotteryInfo, lottery);
		lotteryRepository.save(lottery);
		return lotteryInfo;
	}

	@Override
	public void delete(Long id) {
		lotteryRepository.delete(id);		
	}

}
