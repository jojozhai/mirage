/**
 * 
 */
package com.ymt.mirage.poster.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Entity
public class UserPoster extends DomainImpl {

	/**
	 * 用户id
	 */
	@ManyToOne
	private User user;
	/**
	 * 海报id
	 */
	@ManyToOne
	private Poster poster;
	/**
	 * 过期时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredTime;
	/**
	 * 用户海报的微信mediaId
	 */
	private String weixinMediaId;
	/**
	 * 用户通过此海报获得的积分数 
	 */
	private int pointCount;
	/**
	 * 是否已激活海报对应的事件
	 */
	private boolean active;
	/**
	 * 二维码在阿里OSS服务上的url;
	 */
	@Column(length = 2000)
	private String qrcodeOssUrl;
	/**
	 * 整个海报在阿里OSS服务上的url;
	 */
	@Column(length = 2000)
	private String ossUrl;
	
	public boolean isExpired() {
		return new DateTime(expiredTime).isBeforeNow();
	}
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
	 * @return the poster
	 */
	public Poster getPoster() {
		return poster;
	}
	/**
	 * @param poster the poster to set
	 */
	public void setPoster(Poster poster) {
		this.poster = poster;
	}
	/**
	 * @return the expiredTime
	 */
	public Date getExpiredTime() {
		return expiredTime;
	}
	/**
	 * @param expiredTime the expiredTime to set
	 */
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	/**
	 * @return the mediaId
	 */
	public String getWeixinMediaId() {
		return weixinMediaId;
	}
	/**
	 * @param mediaId the mediaId to set
	 */
	public void setWeixinMediaId(String mediaId) {
		this.weixinMediaId = mediaId;
	}
	/**
	 * @return the qrcodeOssKey
	 */
	public String getQrcodeOssUrl() {
		return qrcodeOssUrl;
	}
	/**
	 * @param qrcodeOssKey the qrcodeOssKey to set
	 */
	public void setQrcodeOssUrl(String qrcodeOssUrl) {
		this.qrcodeOssUrl = qrcodeOssUrl;
	}
	/**
	 * @return the ossUrl
	 */
	public String getOssUrl() {
		return ossUrl;
	}
	/**
	 * @param ossUrl the ossUrl to set
	 */
	public void setOssUrl(String ossUrl) {
		this.ossUrl = ossUrl;
	}
	/**
	 * @return the pointCount
	 */
	public int getPointCount() {
		return pointCount;
	}
	/**
	 * @param pointCount the pointCount to set
	 */
	public void setPointCount(int pointCount) {
		this.pointCount = pointCount;
	}
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
