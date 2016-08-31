/**
 * 
 */
package com.ymt.mirage.sms.support;

import java.util.Date;

/**
 * @author zhailiang
 * @since 2016年6月8日
 */
public class SmsCode {
	
	private String code;
	
	private Date applyTime;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the applyTime
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * @param applyTime the applyTime to set
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
}
