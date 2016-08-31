/**
 * 
 */
package com.ymt.mirage.user.domain;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月21日
 */
@MappedSuperclass
public class Collect<T> extends DomainImpl{
	
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	@ManyToOne
	private T target;
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
	 * @return the target
	 */
	public T getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(T target) {
		this.target = target;
	}
}
