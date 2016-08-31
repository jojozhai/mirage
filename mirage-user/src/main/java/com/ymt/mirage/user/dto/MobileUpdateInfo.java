/**
 * 
 */
package com.ymt.mirage.user.dto;

/**
 * @author zhailiang
 * @since 2016年6月24日
 */
public class MobileUpdateInfo {
	
	private Long userId;
	private String oldMobile;
	private String newMobile;
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
	 * @return the oldMobile
	 */
	public String getOldMobile() {
		return oldMobile;
	}
	/**
	 * @param oldMobile the oldMobile to set
	 */
	public void setOldMobile(String oldMobile) {
		this.oldMobile = oldMobile;
	}
	/**
	 * @return the newMobile
	 */
	public String getNewMobile() {
		return newMobile;
	}
	/**
	 * @param newMobile the newMobile to set
	 */
	public void setNewMobile(String newMobile) {
		this.newMobile = newMobile;
	}

}
