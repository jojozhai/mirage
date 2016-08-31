/**
 * 
 */
package com.ymt.mirage.challenge.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.ymt.mirage.challenge.dto.ParticipatorType;
import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
@Entity
public class Participator extends DomainImpl {
	
	/**
	 * 参与者
	 */
	@ManyToOne
	private User user;
	/**
	 * 参与的挑战
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private UserChallenge challenge;
	/**
	 * 参与方式
	 */
	@Enumerated(EnumType.STRING)
	private ParticipatorType type;
	
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
	 * @return the challenge
	 */
	public UserChallenge getChallenge() {
		return challenge;
	}
	/**
	 * @param challenge the challenge to set
	 */
	public void setChallenge(UserChallenge challenge) {
		this.challenge = challenge;
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

}
