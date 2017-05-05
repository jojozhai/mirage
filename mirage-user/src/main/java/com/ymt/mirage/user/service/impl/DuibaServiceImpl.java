/**
 * 
 */
package com.ymt.mirage.user.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.user.domain.DuibaOrder;
import com.ymt.mirage.user.domain.DuibaOrderStatus;
import com.ymt.mirage.user.domain.DuibaPointStatus;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.DuibaOrderRepository;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.mirage.user.service.DuibaService;
import com.ymt.mirage.user.support.duiba.CreditConsumeParams;
import com.ymt.mirage.user.support.duiba.CreditConsumeResult;
import com.ymt.mirage.user.support.duiba.CreditNotifyParams;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 * @author zhailiang
 *
 */
@Service("duibaService")
@Transactional
public class DuibaServiceImpl implements DuibaService {

	@Autowired
	private DuibaOrderRepository duibaOrderRepository;

	@Autowired
	private UserRepository userRepository;

	private Set<String> consumeKeys = new HashSet<>();
	
	private Set<String> confirmKeys = new HashSet<>();

	@Override
	public CreditConsumeResult consumeCredit(CreditConsumeParams params) {

		CreditConsumeResult result;

		User user = userRepository.findOne(new Long(params.getUid()));
		String orderNum = params.getOrderNum();

		if (!consumeKeys.contains(orderNum)) {
			consumeKeys.add(orderNum);
			result = consumeCredit(params, user, orderNum);
			consumeKeys.remove(orderNum);
		} else {
			result = buildFailResult(user, "并发处理");
		}
		return result;
	}

	private CreditConsumeResult consumeCredit(CreditConsumeParams params, User user, String orderNum) {

		CreditConsumeResult result;

		DuibaOrder duibaOrder = duibaOrderRepository.findByOrderNum(orderNum);
		if (duibaOrder == null) {
			if (user.getPoint() >= params.getCredits()) {
				user.setPoint(user.getPoint() - params.getCredits().intValue());
				duibaOrder = createDuibaOrder(params, user);
				result = buildSuccessResult(duibaOrder, user);
			} else {
				result = buildFailResult(user, "积分不足");
			}
		} else {
			result = buildSuccessResult(duibaOrder, user);
		}

		return result;
	}

	private DuibaOrder createDuibaOrder(CreditConsumeParams params, User user) {
		DuibaOrder duibaOrder;
		duibaOrder = new DuibaOrder();
		duibaOrder.setActualPrice(params.getActualPrice());
		duibaOrder.setCredits(params.getCredits());
		duibaOrder.setDescription(params.getDescription());
		duibaOrder.setOrderNum(params.getOrderNum());
		duibaOrder.setOrderStatus(DuibaOrderStatus.CREATED);
		duibaOrder.setPointStatus(DuibaPointStatus.PENDING);
		duibaOrder.setType(params.getType());
		duibaOrder.setUser(user);
		return duibaOrder;
	}

	private CreditConsumeResult buildFailResult(User user, String message) {
		CreditConsumeResult result;
		result = new CreditConsumeResult(false);
		result.setCredits(new Long(user.getPoint()));
		result.setErrorMessage(message);
		return result;
	}

	private CreditConsumeResult buildSuccessResult(DuibaOrder duibaOrder, User user) {
		CreditConsumeResult result;
		result = new CreditConsumeResult(true);
		result.setBizId(duibaOrderRepository.save(duibaOrder).getId().toString());
		result.setCredits(new Long(user.getPoint()));
		return result;
	}

	@Override
	public void consumeConfirm(CreditNotifyParams params) {
		
		String orderNum = params.getOrderNum();
		
		if (confirmKeys.contains(orderNum)) {
			throw new PzException("并发请求");
		}
		
		confirmKeys.add(orderNum);
		
		DuibaOrder duibaOrder = duibaOrderRepository.findByOrderNum(orderNum);
		
		if(!duibaOrder.getPointStatus().equals(DuibaPointStatus.PENDING)) {
			if(params.isSuccess()){
				//兑换成功
				duibaOrder.setOrderStatus(DuibaOrderStatus.SUCCESS);
				duibaOrder.setPointStatus(DuibaPointStatus.SUCCESS);
		    }else{
		        //兑换失败，对用户的金币进行返还
		    	duibaOrder.setOrderStatus(DuibaOrderStatus.FAIL);
				duibaOrder.setPointStatus(DuibaPointStatus.RETURN);
				User user = duibaOrder.getUser();
				user.setPoint(user.getPoint() + duibaOrder.getCredits().intValue());
		    }
		}
		
		confirmKeys.remove(orderNum);

	}

}
