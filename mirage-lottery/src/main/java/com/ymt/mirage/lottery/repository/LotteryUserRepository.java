/**
 * 
 */
package com.ymt.mirage.lottery.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.lottery.domain.LotteryUser;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
@Repository
public interface LotteryUserRepository extends PzRepository<LotteryUser> {

	LotteryUser findByLotteryIdAndUserId(Long lotteryId, Long userId);

}
