/**
 * 
 */
package com.ymt.mirage.challenge.dto;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
public class ParticipatorInfo {
	
	private Long id;
	/**
	 * 参与人的id
	 */
	private Long userId;
	/**
	 * 参与人昵称
	 */
	private String userName;
	/**
	 * 参与人头像
	 */
	private String userHeadimgurl;
	/**
	 * 用户挑战的id
	 */
	private Long userChallengeId;
	/**
	 * 参与的类型
	 */
	private ParticipatorType type;
	
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
	 * @return the userChallengeId
	 */
	public Long getUserChallengeId() {
		return userChallengeId;
	}
	/**
	 * @param userChallengeId the userChallengeId to set
	 */
	public void setUserChallengeId(Long userChallengeId) {
		this.userChallengeId = userChallengeId;
	}
	/**
	 * @return the type
	 */
	public ParticipatorType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ParticipatorType type) {
		this.type = type;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userHeadimgurl
	 */
	public String getUserHeadimgurl() {
		return userHeadimgurl;
	}
	/**
	 * @param userHeadimgurl the userHeadimgurl to set
	 */
	public void setUserHeadimgurl(String userHeadimgurl) {
		this.userHeadimgurl = userHeadimgurl;
	}

}
