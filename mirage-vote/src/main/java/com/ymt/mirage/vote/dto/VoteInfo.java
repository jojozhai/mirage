/**
 * 
 */
package com.ymt.mirage.vote.dto;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
public class VoteInfo {
	
	private Long id;
	/**
	 * 报名
	 */
	private Long enrollId;
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the enrollId
	 */
	public Long getEnrollId() {
		return enrollId;
	}
	/**
	 * @param enrollId the enrollId to set
	 */
	public void setEnrollId(Long enrollId) {
		this.enrollId = enrollId;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
