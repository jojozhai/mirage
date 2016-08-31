/**
 * 
 */
package com.ymt.mirage.poster.dto;

import java.util.Date;

/**
 * @author zhailiang
 * @since 2016年5月7日
 */
public class UserPosterInfo {
	
	private Long id;
	
	private Long posterId;
	
	private String nickname;
	
	private Date expiredTime;
	
	private int pointCount;

	/**
	 * @return the posterId
	 */
	public Long getPosterId() {
		return posterId;
	}

	/**
	 * @param posterId the posterId to set
	 */
	public void setPosterId(Long posterId) {
		this.posterId = posterId;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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

}
