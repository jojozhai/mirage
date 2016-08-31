/**
 * 
 */
package com.ymt.mirage.sms.service;

/**
 * @author zhailiang
 * @since 2016年6月8日
 */
public interface SmsService {

	void sendSmsCode(String phone);

	void checkSmsCode(String phone, String code);

}
