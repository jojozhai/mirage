/**
 * 
 */
package com.ymt.mirage.meilang.dto;

import java.math.BigDecimal;
import java.util.List;

import com.ymt.mirage.tag.dto.TagInfo;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public class VideoInfo {

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
	 * 时长
	 */
	private String duration;
	/**
	 * 视频url
	 */
	private String url;
	/**
	 * 视频url
	 */
	private String playUrl;
	/**
	 * 选中的标签
	 */
	private List<TagInfo> tags;
	/**
	 * 所属套餐
	 */
	private List<VideoSetInfo> setInfos;
	/**
	 * 购买人次
	 */
	private int buyerCount;
	/**
	 * 播放次数
	 */
	private int playCount;
	/**
	 * 
	 */
	private boolean hasPermission;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the tags
	 */
	public List<TagInfo> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<TagInfo> tags) {
		this.tags = tags;
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
	 * @return the sets
	 */
	public List<VideoSetInfo> getSetInfos() {
		return setInfos;
	}
	/**
	 * @param sets the sets to set
	 */
	public void setSetInfos(List<VideoSetInfo> sets) {
		this.setInfos = sets;
	}
	/**
	 * @return the playCount
	 */
	public int getPlayCount() {
		return playCount;
	}
	/**
	 * @param playCount the playCount to set
	 */
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	/**
	 * @return the palyUrl
	 */
	public String getPlayUrl() {
		return playUrl;
	}
	/**
	 * @param palyUrl the palyUrl to set
	 */
	public void setPlayUrl(String palyUrl) {
		this.playUrl = palyUrl;
	}
	/**
	 * @return the hasPermission
	 */
	public boolean isHasPermission() {
		return hasPermission;
	}
	/**
	 * @param hasPermission the hasPermission to set
	 */
	public void setHasPermission(boolean hasPermission) {
		this.hasPermission = hasPermission;
	}
	
}
