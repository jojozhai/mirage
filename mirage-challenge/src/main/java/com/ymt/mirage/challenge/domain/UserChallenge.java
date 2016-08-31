/**
 * 
 */
package com.ymt.mirage.challenge.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ymt.mirage.challenge.dto.ChallengeStatus;
import com.ymt.mirage.challenge.dto.ChallengeType;
import com.ymt.mirage.challenge.dto.Remind;
import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Entity
public class UserChallenge extends DomainImpl {

	/**
	 * 发起者
	 */
	@ManyToOne
	private User user;
	/**
	 * 挑战id,只有参加官方挑战时有值
	 */
	@ManyToOne
	private Challenge challenge;
	/**
	 * 状态
	 */
	@Enumerated(EnumType.STRING)
	private ChallengeStatus status;
	/**
	 * 挑战名称
	 */
	private String name;
	/**
	 * 挑战描述
	 */
	private String desc;
	/**
	 * 挑战持续天数
	 */
	private int days;
	/**
	 * 当前挑战的进度
	 */
	private int progress;
	/**
	 * 提醒设置
	 */
	@Embedded
	private Remind remind;
	/**
	 * 私密挑战
	 */
	private boolean privacy;
	/**
	 * 押金
	 */
	private BigDecimal pledge;
	/**
	 * 开始日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	/**
	 * 结束日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	/**
	 * 挑战类型
	 */
	@Enumerated(EnumType.STRING)
	private ChallengeType type;
	/**
	 * 参与者总数
	 */
	private int participatorCount;
	/**
	 * 监督者总数
	 */
	private int overseerCount;
	/**
	 * 二维码地址
	 */
	private String qrcodeOssUrl;
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}
	/**
	 * @param progress the progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}
	/**
	 * @return the privacy
	 */
	public boolean isPrivacy() {
		return privacy;
	}
	/**
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(boolean privacy) {
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the finishDate
	 */
	public Date getFinishDate() {
		return finishDate;
	}
	/**
	 * @param finishDate the finishDate to set
	 */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
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
	 * @return the challenge
	 */
	public Challenge getChallenge() {
		return challenge;
	}
	/**
	 * @param challenge the challenge to set
	 */
	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}
	/**
	 * @return the participatorCount
	 */
	public int getParticipatorCount() {
		return participatorCount;
	}
	/**
	 * @param participatorCount the participatorCount to set
	 */
	public void setParticipatorCount(int participatorCount) {
		this.participatorCount = participatorCount;
	}
	/**
	 * @return the overseerCount
	 */
	public int getOverseerCount() {
		return overseerCount;
	}
	/**
	 * @param overseerCount the overseerCount to set
	 */
	public void setOverseerCount(int overseerCount) {
		this.overseerCount = overseerCount;
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
