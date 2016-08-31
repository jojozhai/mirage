/**
 * 
 */
package com.ymt.mirage.poster.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月7日
 */
@Entity
public class UserPosterScan extends DomainImpl {

	/**
	 * 用户海报
	 */
	@ManyToOne
	private UserPoster userPoster;
	/**
	 * 扫描者 
	 */
	@ManyToOne
	private User scaner;
	/**
	 * @return the userPoster
	 */
	public UserPoster getUserPoster() {
		return userPoster;
	}
	/**
	 * @param userPoster the userPoster to set
	 */
	public void setUserPoster(UserPoster userPoster) {
		this.userPoster = userPoster;
	}
	/**
	 * @return the scaner
	 */
	public User getScaner() {
		return scaner;
	}
	/**
	 * @param scaner the scaner to set
	 */
	public void setScaner(User scaner) {
		this.scaner = scaner;
	}
	
}
