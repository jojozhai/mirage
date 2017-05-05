/**
 * 
 */
package com.ymt.mirage.user.service;

import com.ymt.mirage.user.support.duiba.CreditConsumeParams;
import com.ymt.mirage.user.support.duiba.CreditConsumeResult;
import com.ymt.mirage.user.support.duiba.CreditNotifyParams;

/**
 * @author zhailiang
 *
 */
public interface DuibaService {
	
	CreditConsumeResult consumeCredit(CreditConsumeParams params);

	void consumeConfirm(CreditNotifyParams params);

}
