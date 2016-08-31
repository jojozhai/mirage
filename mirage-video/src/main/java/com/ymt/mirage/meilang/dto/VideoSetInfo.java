/**
 * 
 */
package com.ymt.mirage.meilang.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public class VideoSetInfo {

	/**
	 * 
	 */
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 简介
	 */
	private String desc;
	/**
	 * 精选
	 */
	private Boolean selection;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 视频图片
	 */
	private String image;
	/**
	 * 购买人次
	 */
	private int buyerCount;
	/**
	 * 
	 */
	private List<VideoInfo> videoInfos;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * @return the buyerCount
	 */
	public int getBuyerCount() {
		return buyerCount;
	}
	/**
	 * @param buyerCount the buyerCount to set
	 */
	public void setBuyerCount(int buyerCount) {
		this.buyerCount = buyerCount;
	}
	/**
	 * @return the videoInfos
	 */
	public List<VideoInfo> getVideoInfos() {
		return videoInfos;
	}
	/**
	 * @param videoInfos the videoInfos to set
	 */
	public void setVideoInfos(List<VideoInfo> videoInfos) {
		this.videoInfos = videoInfos;
	}
	
}
