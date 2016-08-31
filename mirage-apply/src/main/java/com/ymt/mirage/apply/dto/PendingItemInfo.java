/**
 * 
 */
package com.ymt.mirage.apply.dto;

import java.util.Date;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
public class PendingItemInfo {
	
	private Long id;
	private Date createdTime = new Date();
	private Long senderId;
	private String senderName;
	private String senderHeadimgurl;
	private Long receiverId;
	private String receiverName;
	private String receiverHeadimgurl;
	private String content;
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
	 * @return the senderId
	 */
	public Long getSenderId() {
		return senderId;
	}
	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}
	/**
	 * @param senderName the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	/**
	 * @return the senderHeadimgurl
	 */
	public String getSenderHeadimgurl() {
		return senderHeadimgurl;
	}
	/**
	 * @param senderHeadimgurl the senderHeadimgurl to set
	 */
	public void setSenderHeadimgurl(String senderHeadimgurl) {
		this.senderHeadimgurl = senderHeadimgurl;
	}
	/**
	 * @return the receiverId
	 */
	public Long getReceiverId() {
		return receiverId;
	}
	/**
	 * @param receiverId the receiverId to set
	 */
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	/**
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}
	/**
	 * @param receiverName the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	/**
	 * @return the receiverHeadimgurl
	 */
	public String getReceiverHeadimgurl() {
		return receiverHeadimgurl;
	}
	/**
	 * @param receiverHeadimgurl the receiverHeadimgurl to set
	 */
	public void setReceiverHeadimgurl(String receiverHeadimgurl) {
		this.receiverHeadimgurl = receiverHeadimgurl;
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

}
