/**
 * 
 */
package com.ymt.mirage.vote.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
@Entity
public class VoteActivity extends DomainImpl {
	
	/**
	 * 活动名称
	 */
	private String name;
	/**
	 * 活动图片
	 */
	@ElementCollection
	private List<String> images = new ArrayList<String>();
	/**
	 * 开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	/**
	 * 结束时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	/**
	 * 报名人数
	 */
	private int enrollCount;
	/**
	 * 投票人次
	 */
	private int voteCount;
	/**
	 * 访问量
	 */
	private int browseCount;
	/**
	 * 活动详情
	 */
	@Lob
	private String desc;
	/**
	 * 活动奖项
	 */
	@Lob
	private String gift;
	/**
	 * 活动声明
	 */
	@Lob
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
	 * @return the enrollCount
	 */
	public int getEnrollCount() {
		return enrollCount;
	}
	/**
	 * @param enrollCount the enrollCount to set
	 */
	public void setEnrollCount(int enrollCount) {
		this.enrollCount = enrollCount;
	}
	/**
	 * @return the voteCount
	 */
	public int getVoteCount() {
		return voteCount;
	}
	/**
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	/**
	 * @return the browseCount
	 */
	public int getBrowseCount() {
		return browseCount;
	}
	/**
	 * @param browseCount the browseCount to set
	 */
	public void setBrowseCount(int browseCount) {
		this.browseCount = browseCount;
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
