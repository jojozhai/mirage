/**
 * 
 */
package com.ymt.mirage.social.dto;

import java.util.Date;

import com.ymt.mirage.social.domain.Comment;
import com.ymt.mirage.social.domain.Praise;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
public class PostInfo {
	
	private Long id;
	/**
	 * 
	 */
	private Date createdTime = new Date();
	/**
	 * 发post的人
	 */
	private Long createrId;
	/**
	 * 发post的人的类型
	 */
	private String createrType;
	/**
	 * 发送人姓名
	 */
	private String createrName;
	/**
	 * 发送人头像
	 */
	private String createrHeadimgurl;
	/**
	 * 容器id,容器可能是一个社群,一个挑战
	 */
	private Long containerId;
	/**
	 * 容器类型
	 */
	private String containerType;
	/**
	 * post的图片
	 */
	private String images;
	/**
	 * post的内容
	 */
	private String content;
	/**
	 * 
	 */
	private Praise[] praises = new Praise[]{};
	/**
	 * 
	 */
	private Comment[] comments = new Comment[]{};
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
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * @return the createrId
	 */
	public Long getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	/**
	 * @return the createrType
	 */
	public String getCreaterType() {
		return createrType;
	}
	/**
	 * @param createrType the createrType to set
	 */
	public void setCreaterType(String createrType) {
		this.createrType = createrType;
	}
	/**
	 * @return the createrName
	 */
	public String getCreaterName() {
		return createrName;
	}
	/**
	 * @param createrName the createrName to set
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	/**
	 * @return the createrHeadimgurl
	 */
	public String getCreaterHeadimgurl() {
		return createrHeadimgurl;
	}
	/**
	 * @param createrHeadimgurl the createrHeadimgurl to set
	 */
	public void setCreaterHeadimgurl(String createrHeadimgurl) {
		this.createrHeadimgurl = createrHeadimgurl;
	}
	/**
	 * @return the containerId
	 */
	public Long getContainerId() {
		return containerId;
	}
	/**
	 * @param containerId the containerId to set
	 */
	public void setContainerId(Long containerId) {
		this.containerId = containerId;
	}
	/**
	 * @return the containerType
	 */
	public String getContainerType() {
		return containerType;
	}
	/**
	 * @param containerType the containerType to set
	 */
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	/**
	 * @return the images
	 */
	public String getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(String images) {
		this.images = images;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the praises
	 */
	public Praise[] getPraises() {
		return praises;
	}
	/**
	 * @param praises the praises to set
	 */
	public void setPraises(Praise[] praises) {
		this.praises = praises;
	}
	/**
	 * @return the comments
	 */
	public Comment[] getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(Comment[] comments) {
		this.comments = comments;
	}
	
}
