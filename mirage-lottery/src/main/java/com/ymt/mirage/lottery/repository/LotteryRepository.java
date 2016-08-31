/**
 * 
 */
package com.ymt.mirage.lottery.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.lottery.domain.Lottery;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
@Repository
public interface LotteryRepository extends PzRepository<Lottery> {

}
