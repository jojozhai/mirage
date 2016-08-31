/**
 * 
 */
package com.ymt.mirage.vote.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
public class VoteActivityInfo {
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 活动名称
	 */
	private String name;
	/**
	 * 活动图片
	 */
	private List<String> images = new ArrayList<String>();
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 报名人数
	 */
	private Integer enrollCount;
	/**
	 * 投票人次
	 */
	private Integer voteCount;
	/**
	 * 访问量
	 */
	private Integer browseCount;
	/**
	 * 活动详情
	 */
	private String desc;
	/**
	 * 活动奖项
	 */
	private String gift;
	/**
	 * 活动声明
	 */
	private String declare;
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
	 * @return the image
	 */
	public List<String> getImages() {
		return images;
	}
	/**
	 * @param image the image to set
	 */
	public void setImages(List<String> images) {
		this.images = images;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @param enrollCount the enrollCount to set
	 */
	public void setEnrollCount(Integer enrollCount) {
		this.enrollCount = enrollCount;
	}
	/**
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	/**
	 * @param browseCount the browseCount to set
	 */
	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}
	/**
	 * @return the enrollCount
	 */
	public Integer getEnrollCount() {
		return enrollCount;
	}
	/**
	 * @return the voteCount
	 */
	public Integer getVoteCount() {
		return voteCount;
	}
	/**
	 * @return the browseCount
	 */
	public Integer getBrowseCount() {
		return browseCount;
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
	 * @return the gift
	 */
	public String getGift() {
		return gift;
	}
	/**
	 * @param gift the gift to set
	 */
	public void setGift(String gift) {
		this.gift = gift;
	}
	/**
	 * @return the declare
	 */
	public String getDeclare() {
		return declare;
	}
	/**
	 * @param declare the declare to set
	 */
	public void setDeclare(String declare) {
		this.declare = declare;
	}

}
