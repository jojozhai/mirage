/**
 * 
 */
package com.ymt.mirage.sms.spi;

/**
 * @author zhailiang
 * @since 2016年6月8日
 */
public interface SmsProcessor {

	String getSmsCodeMessage(String phone);

	void send(String phone, String message);

	void check(String phone, String code);

}
