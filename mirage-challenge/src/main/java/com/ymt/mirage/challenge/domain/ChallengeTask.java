/**
 * 
 */
package com.ymt.mirage.challenge.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ymt.pz365.data.jpa.domain.SortableImpl;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Entity
public class ChallengeTask extends SortableImpl {
	
	/**
	 * 任务所属的挑战
	 */
	@ManyToOne
	private Challenge challenge;
	/**
	 * 任务名称
	 */
	private String name;
	/**
	 * 任务描述 
	 */
	private String desc;
	/**
	 * 图片
	 */
	private String image;
	
	/**
	 * @return the challenge
	 */
	public Challenge getChallenge() {
		return challenge;
	}
	/**
	 * @param challenge the challenge to set
	 */
	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
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

}
