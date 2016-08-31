/**
 * 
 */
package com.ymt.mirage.user.domain;

import javax.persistence.Entity;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@Entity
public class Level extends DomainImpl {

	/**
	 * 等级名称
	 */
	private String name;
	/**
	 * 积分数
	 */
	private int point;
	
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
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	
}
