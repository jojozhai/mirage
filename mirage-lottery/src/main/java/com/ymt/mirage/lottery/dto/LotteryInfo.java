/**
 * 
 */
package com.ymt.mirage.lottery.dto;

import java.util.Date;
import java.util.List;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
public class LotteryInfo {
	
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 过期时间
	 */
	private Date expiredDate;
	/**
	 * 
	 */
	private List<String> prizes;
	/**
	 * 
	 */
	private List<String> colors;
	/**
	 * 
	 */
	private String desc;
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
	 * @return the expiredDate
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}
	/**
	 * @param expiredDate the expiredDate to set
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	/**
	 * @return the prizes
	 */
	public List<String> getPrizes() {
		return prizes;
	}
	/**
	 * @param prizes the prizes to set
	 */
	public void setPrizes(List<String> prizes) {
		this.prizes = prizes;
	}
	/**
	 * @return the colors
	 */
	public List<String> getColors() {
		return colors;
	}
	/**
	 * @param colors the colors to set
	 */
	public void setColors(List<String> colors) {
		this.colors = colors;
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

}
