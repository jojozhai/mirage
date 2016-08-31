/**
 * 
 */
package com.ymt.mirage.challenge.dto;

import java.math.BigDecimal;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public class UserChallengeInfo {

	private Long id;
	/**
	 * 发起者
	 */
	private Long userId;
	/**
	 * 昵称
	 */
	private String userNickname;
	/**
	 * 头像
	 */
	private String userHeadimgurl;
	/**
	 * 挑战id
	 */
	private Long challengeId;
	/**
	 * 
	 */
	private ChallengeType type;
	/**
	 * 挑战名称
	 */
	private String name;
	/**
	 * 挑战持续天数
	 */
	private Integer days;
	/**
	 * 当前挑战的进度
	 */
	private Integer progress;
	/**
	 * 提醒设置
	 */
	private Remind remind;
	/**
	 * 私密挑战
	 */
	private Boolean privacy;
	/**
	 * 押金
	 */
	private BigDecimal pledge;
	/**
	 * 参与者总数
	 */
	private Integer participatorCount;
	/**
	 * 监督者剩余总数
	 */
	private Integer overseerResidue;
	/**
	 * 状态
	 */
	private ChallengeStatus status;
	/**
	 * 二维码地址
	 */
	private String qrcodeOssUrl;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(Integer days) {
		this.days = days;
	}
	/**
	 * @return the remind
	 */
	public Remind getRemind() {
		return remind;
	}
	/**
	 * @param remind the remind to set
	 */
	public void setRemind(Remind remind) {
		this.remind = remind;
	}
	/**
	 * @return the privacy
	 */
	public Boolean getPrivacy() {
		return privacy;
	}
	/**
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(Boolean privacy) {
		this.privacy = privacy;
	}
	/**
	 * @return the pledge
	 */
	public BigDecimal getPledge() {
		return pledge;
	}
	/**
	 * @param pledge the pledge to set
	 */
	public void setPledge(BigDecimal pledge) {
		this.pledge = pledge;
	}
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
	 * @return the challengeId
	 */
	public Long getChallengeId() {
		return challengeId;
	}
	/**
	 * @param challengeId the challengeId to set
	 */
	public void setChallengeId(Long challengeId) {
		this.challengeId = challengeId;
	}
	/**
	 * @return the type
	 */
	public ChallengeType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ChallengeType type) {
		this.type = type;
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
	/**
	 * @return the progress
	 */
	public Integer getProgress() {
		return progress;
	}
	/**
	 * @param progress the progress to set
	 */
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	/**
	 * @return the participatorCount
	 */
	public Integer getParticipatorCount() {
		return participatorCount;
	}
	/**
	 * @param participatorCount the participatorCount to set
	 */
	public void setParticipatorCount(Integer participatorCount) {
		this.participatorCount = participatorCount;
	}
	/**
	 * @return the overseerCount
	 */
	public Integer getOverseerResidue() {
		return overseerResidue;
	}
	/**
	 * @param overseerCount the overseerCount to set
	 */
	public void setOverseerResidue(Integer overseerCount) {
		this.overseerResidue = overseerCount;
	}
	/**
	 * @return the status
	 */
	public ChallengeStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}
	/**
	 * @return the qrcodeOssUrl
	 */
	public String getQrcodeOssUrl() {
		return qrcodeOssUrl;
	}
	/**
	 * @param qrcodeOssUrl the qrcodeOssUrl to set
	 */
	public void setQrcodeOssUrl(String qrcodeOssUrl) {
		this.qrcodeOssUrl = qrcodeOssUrl;
	}
}
