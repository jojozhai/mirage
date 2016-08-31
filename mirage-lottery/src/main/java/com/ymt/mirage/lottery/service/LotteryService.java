/**
 * 
 */
package com.ymt.mirage.lottery.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.lottery.dto.LotteryInfo;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
public interface LotteryService {
	
	Page<LotteryInfo> query(LotteryInfo lotteryInfo, Pageable pageable);
	
	LotteryInfo create(LotteryInfo lotteryInfo);

	LotteryInfo getInfo(Long id);

	LotteryInfo update(LotteryInfo lotteryInfo);

	void delete(Long id);

}
