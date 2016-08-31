/**
 * 
 */
package com.ymt.mirage.lottery.dto;

import java.util.Date;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
public class LotteryUserInfo {

	private Long id;
	private Date createdTime = new Date();
	private Long lotteryId;
	private Long userId;
	private String userNickname;
	private String userRealname;
	private String userMobile;
	private String prize;
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
	 * @return the lotteryId
	 */
	public Long getLotteryId() {
		return lotteryId;
	}
	/**
	 * @param lotteryId the lotteryId to set
	 */
	public void setLotteryId(Long lotteryId) {
		this.lotteryId = lotteryId;
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
	/**
	 * @return the prize
	 */
	public String getPrize() {
		return prize;
	}
	/**
	 * @param prize the prize to set
	 */
	public void setPrize(String prize) {
		this.prize = prize;
	}
	/**
	 * @return the userNickname
	 */
	public String getUserNickname() {
		return userNickname;
	}
	/**
	 * @param userNickname the userNickname to set
	 */
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	/**
	 * @return the userRealname
	 */
	public String getUserRealname() {
		return userRealname;
	}
	/**
	 * @param userRealname the userRealname to set
	 */
	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}
	/**
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}
	/**
	 * @param userMobile the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
