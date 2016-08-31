/**
 * 
 */
package com.ymt.mirage.vote.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 报名信息
 * @author zhailiang
 * @since 2016年4月29日
 */
public class EnrollInfo {
	/**
	 * 
	 */
	private Long id;
	/**
	 * 报名的活动
	 */
	private Long activityId;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 编号
	 */
	private Integer number;
	/**
	 * 票数
	 */
	private Integer voteCount;
	/**
	 * 个人留言
	 */
	private String desc;
	/**
	 * 图片
	 */
	private List<String> images = new ArrayList<String>();
	
	/**
	 * @return the activityId
	 */
	
	public Long getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	/**
	 * @return the phone
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * @return the voteCount
	 */
	public Integer getVoteCount() {
		return voteCount;
	}
	/**
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
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
	 * @return the images
	 */
	public List<String> getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(List<String> images) {
		this.images = images;
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
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
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

}
