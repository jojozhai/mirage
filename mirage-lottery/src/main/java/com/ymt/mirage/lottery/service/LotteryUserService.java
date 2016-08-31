/**
 * 
 */
package com.ymt.mirage.lottery.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.lottery.dto.LotteryUserInfo;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
public interface LotteryUserService {
	
	Page<LotteryUserInfo> query(LotteryUserInfo lotteryUserInfo, Pageable pageable);
	
	LotteryUserInfo create(LotteryUserInfo lotteryUserInfo);

	LotteryUserInfo getInfo(Long id);

	LotteryUserInfo update(LotteryUserInfo lotteryUserInfo);

	void delete(Long id);

}
