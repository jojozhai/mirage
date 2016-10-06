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

	@Deprecated
	void send(String phone, String message);
	
	void send(String phone, String cid, String[] params);

	void check(String phone, String code);

}
