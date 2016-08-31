/**
 * 
 */
package com.ymt.mirage.sms.spi;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;

import com.ymt.mirage.sms.support.SmsCode;
import com.ymt.pz365.framework.core.config.cache.CacheNames;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 * @author zhailiang
 * @since 2016年6月8日
 */
public abstract class AbstractSmsProcessor implements SmsProcessor {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public String getSmsCodeMessage(String phone) {
		
		SmsCode smsCode = cacheManager.getCache(CacheNames.CODE_SMS).get(phone, SmsCode.class);
		if(smsCode != null) {
			if(new DateTime(smsCode.getApplyTime()).plusMinutes(getSendIntervalMinute()).isAfterNow()){
				throw new PzException("请求验证码频率过快,请稍后再试");
			}
		}
		return createSmsCode(phone); //String.format(getSmsCodeMessageTemplate(), createSmsCode(phone));
		
	}
	
	@Override
	public void check(String phone, String code) {
		SmsCode smsCode = cacheManager.getCache(CacheNames.CODE_SMS).get(phone, SmsCode.class);
		if(smsCode == null){
			throw new PzException("验证码校验失败");
		}
		if(new DateTime(smsCode.getApplyTime()).plusMinutes(getExpiredMinute()).isBeforeNow()){
			throw new PzException("验证码校验失败");
		}
		if(!StringUtils.equals(code, smsCode.getCode()) && isProductEnv()){
			throw new PzException("验证码校验失败");
		}
	}

	private boolean isProductEnv() {
//		return ArrayUtils.contains(applicationContext.getEnvironment().getActiveProfiles(), "pro") ;
	    return true;
	}

	/**
	 * 发送间隔时间
	 * @return
	 * @author zhailiang
	 * @since 2016年6月8日
	 */
	private int getSendIntervalMinute() {
		return 1;
	}

	/**
	 * @param phone
	 * @return
	 * @author zhailiang
	 * @since 2016年6月8日
	 */
	private String createSmsCode(String phone) {
		SmsCode smsCode;
		String code = getRandomCode();
		smsCode = new SmsCode();
		smsCode.setApplyTime(new Date());
		smsCode.setCode(code);
		cacheManager.getCache(CacheNames.CODE_SMS).put(phone, smsCode);
		return code;
	}

	/**
	 * 验证码失效时间，默认十分钟
	 * @return
	 * @author zhailiang
	 * @since 2016年6月8日
	 */
	protected int getExpiredMinute() {
		return 10;
	}

	/**
	 * 获取随机验证码
	 * @return
	 * @author zhailiang
	 * @since 2016年6月8日
	 */
	protected String getRandomCode() {
		return RandomStringUtils.randomNumeric(6);
	}

	/**
	 * 验证码模板
	 * @return
	 * @author zhailiang
	 * @since 2016年6月8日
	 */
	protected String getSmsCodeMessageTemplate() {
		return "您的验证码是:%s";
	}

	@Override
	public void send(String phone, String message) {
		System.out.println("发送短信!!! "+message+".(还没实现...)");
	}
	
	

}
