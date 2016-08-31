/**
 * 
 */
package com.ymt.mirage.user.dto;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
public class LevelInfo {

	/**
	 * 
	 */
	private Long id;
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
	
}
