/**
 * 
 */
package com.ymt.mirage.challenge.dto;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
public enum ChallengeStatus {
	
	/**
	 * 待处理，创建完毕，但还没有监督者时的状态
	 */
	INIT,
	/**
	 * 进行中，有了监督者
	 */
	PROGRESSING,
	/**
	 * 成功
	 */
	SUCCESS,
	/**
	 * 失败
	 */
	FAIL

}
