/**
 * 
 */
package com.ymt.mirage.lottery.domain;

import javax.persistence.Embeddable;

/**
 * @author zhailiang
 * @since 2016年6月28日
 */
@Embeddable
public class Prize {
	
	private String name;
	
	private int rate;
	
	private int count;

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
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
}
