/**
 * 
 */
package com.ymt.mirage.lottery.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
@Entity
public class LotteryUser extends DomainImpl {
	
	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Lottery lottery;
	@ManyToOne
	private User user;
	/**
	 * 
	 */
	private String prize;
	/**
	 * @return the lottery
	 */
	public Lottery getLottery() {
		return lottery;
	}
	/**
	 * @param lottery the lottery to set
	 */
	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
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

}
