/**
 * 
 */
package com.ymt.mirage.vote.domain;

import javax.persistence.Entity;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
@Entity
public class Vote extends DomainImpl {
	
	/**
	 * 报名
	 */
	private Long enrollId;
	/**
	 * 活动
	 */
	private Long activityId;
	/**
	 * 用户id
	 */
	private Long userId;
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
	 * @return the activityId
	 */
	public Long getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
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
