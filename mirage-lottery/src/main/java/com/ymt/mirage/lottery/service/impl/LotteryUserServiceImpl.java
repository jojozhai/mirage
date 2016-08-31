/**
 * 
 */
package com.ymt.mirage.lottery.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.lottery.domain.Lottery;
import com.ymt.mirage.lottery.domain.LotteryUser;
import com.ymt.mirage.lottery.dto.LotteryUserInfo;
import com.ymt.mirage.lottery.repository.LotteryRepository;
import com.ymt.mirage.lottery.repository.LotteryUserRepository;
import com.ymt.mirage.lottery.repository.spec.LotteryUserSpec;
import com.ymt.mirage.lottery.service.LotteryUserService;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
@Service("lotteryUserService")
@Transactional
public class LotteryUserServiceImpl implements LotteryUserService {

	@Autowired
	private LotteryRepository lotteryepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LotteryUserRepository lotteryUserRepository;
	
	@Override
	public Page<LotteryUserInfo> query(LotteryUserInfo lotteryUserInfo, Pageable pageable) {
		Page<LotteryUser> pageData = lotteryUserRepository.findAll(new LotteryUserSpec(lotteryUserInfo), pageable);
		return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<LotteryUser, LotteryUserInfo>() {
			@Override
			protected void doConvert(LotteryUser domain, LotteryUserInfo info) throws Exception {
				setInfoFields(domain, info);
			}
		});
	}

	private void setInfoFields(LotteryUser domain, LotteryUserInfo info) {
		info.setLotteryId(domain.getLottery().getId());
		info.setUserId(domain.getUser().getId());
		info.setUserNickname(domain.getUser().getNickname());
		info.setUserRealname(domain.getUser().getRealname());
		info.setUserMobile(domain.getUser().getMobile());
	}
	
	@Override
	public LotteryUserInfo create(LotteryUserInfo lotteryUserInfo) {
		LotteryUser lotteryUser = lotteryUserRepository.findByLotteryIdAndUserId(lotteryUserInfo.getLotteryId(), lotteryUserInfo.getUserId());
		if(lotteryUser == null) {
			
			Lottery lottery = lotteryepository.getOne(lotteryUserInfo.getLotteryId());
			if(new DateTime().isAfter(lottery.getExpiredDate().getTime())) {
				throw new PzException("活动已结束");
			}
			
			lotteryUser = new LotteryUser();
			BeanUtils.copyProperties(lotteryUserInfo, lotteryUser);
			lotteryUser.setLottery(lottery);
			lotteryUser.setUser(userRepository.getOne(lotteryUserInfo.getUserId()));
			lotteryUserInfo.setId(lotteryUserRepository.save(lotteryUser).getId());
			return lotteryUserInfo;
		}else{
			throw new PzException("您已经抽过奖了");
		}
	}

	@Override
	public LotteryUserInfo getInfo(Long id) {
		LotteryUser lotteryUser = lotteryUserRepository.findOne(id);
		LotteryUserInfo info = new LotteryUserInfo();
		BeanUtils.copyProperties(lotteryUser, info);
		setInfoFields(lotteryUser, info);
		return info;
	}

	@Override
	public LotteryUserInfo update(LotteryUserInfo lotteryUserInfo) {
		LotteryUser lotteryUser = lotteryUserRepository.findByLotteryIdAndUserId(lotteryUserInfo.getLotteryId(), lotteryUserInfo.getUserId());
		lotteryUser.setPrize(lotteryUserInfo.getPrize());
		lotteryUserRepository.save(lotteryUser);
		return lotteryUserInfo;
	}

	@Override
	public void delete(Long id) {
		lotteryUserRepository.delete(id);		
	}
}
