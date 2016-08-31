/**
 * 
 */
package com.ymt.mirage.vote.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * 报名信息
 * @author zhailiang
 * @since 2016年4月29日
 */
@Entity
public class Enroll extends DomainImpl {
	
	/**
	 * 报名的活动
	 */
	@ManyToOne
	private VoteActivity activity;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 编号
	 */
	private int number;
	/**
	 * 票数
	 */
	private int voteCount;
	/**
	 * 
	 */
	@Lob
	private String desc;
	/**
	 * 图片
	 */
	@ElementCollection
	private List<String> images = new ArrayList<String>();
	
	/**
	 * @return the activity
	 */
	public VoteActivity getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(VoteActivity activity) {
		this.activity = activity;
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
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
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
