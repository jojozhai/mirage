/**
 * 
 */
package com.ymt.mirage.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymt.mirage.sms.service.SmsService;
import com.ymt.mirage.sms.spi.SmsProcessor;

/**
 * @author zhailiang
 * @since 2016年6月8日
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {
	
	@Autowired
	private SmsProcessor smsProcessor;

	@Override
	public void sendSmsCode(String phone) {
		smsProcessor.send(phone, smsProcessor.getSmsCodeMessage(phone));
	}

	@Override
	public void checkSmsCode(String phone, String code) {
		smsProcessor.check(phone, code);
	}

}
