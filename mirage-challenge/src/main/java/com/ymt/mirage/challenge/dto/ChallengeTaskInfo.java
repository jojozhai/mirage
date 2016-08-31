/**
 * 
 */
package com.ymt.mirage.challenge.dto;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public class ChallengeTaskInfo {
	
	private Long id;
	/**
	 * 任务所属的挑战
	 */
	private Long challengeId;
	/**
	 * 
	 */
	private String challengeName;
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
	/**
	 * @return the challengeId
	 */
	public Long getChallengeId() {
		return challengeId;
	}
	/**
	 * @param challengeId the challengeId to set
	 */
	public void setChallengeId(Long challengeId) {
		this.challengeId = challengeId;
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
	 * @return the challengeName
	 */
	public String getChallengeName() {
		return challengeName;
	}
	/**
	 * @param challengeName the challengeName to set
	 */
	public void setChallengeName(String challengeName) {
		this.challengeName = challengeName;
	}

}
