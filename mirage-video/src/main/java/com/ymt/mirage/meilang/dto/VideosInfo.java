/**
 * 
 */
package com.ymt.mirage.meilang.dto;

import java.math.BigDecimal;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public class VideosInfo {
	
	private Long id;
	
	private Long setId;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 精选
	 */
	private Boolean selection;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 时长
	 */
	private String duration;
	
	private int index;

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
	 * @return the setId
	 */
	public Long getSetId() {
		return setId;
	}

	/**
	 * @param setId the setId to set
	 */
	public void setSetId(Long setId) {
		this.setId = setId;
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
	 * @return the selection
	 */
	public Boolean getSelection() {
		return selection;
	}

	/**
	 * @param selection the selection to set
	 */
	public void setSelection(Boolean selection) {
		this.selection = selection;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
}
