/**
 * 
 */
package com.ymt.mirage.article.dto;

import java.util.Date;
import java.util.List;

import com.ymt.mirage.tag.dto.TagInfo;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
public class ArticleInfo {
	
	private Long id;
	/**
     * 商业推文
     */
    private Boolean business;
	/**
	 * 分类id
	 */
	private Long tagId;
	/**
	 * 审计日志，记录条目创建时间，自动赋值，不需要程序员手工赋值
	 */
	private Date createdTime = new Date();
	/**
	 * 
	 */
	private Date modifyTime;
	/**
	 * 发布
	 */
	private Boolean enable;
	/**
     * 发布日期
     */
    private Date enableDate;
	/**
	 * 置顶
	 */
	private Boolean top;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 
	 */
	private String image2;
	/**
	 * 阅读量
	 */
	private int readCount;
	/**
	 * 评论数
	 */
	private int commentCount;
	/**
	 * 负责人
	 */
	private String principal;
	/**
	 * 
	 */
	private List<TagInfo> tagInfos;
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
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * @return the enable
	 */
	public Boolean getEnable() {
		return enable;
	}
	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	/**
	 * @return the top
	 */
	public Boolean getTop() {
		return top;
	}
	/**
	 * @param top the top to set
	 */
	public void setTop(Boolean top) {
		this.top = top;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the readCount
	 */
	public int getReadCount() {
		return readCount;
	}
	/**
	 * @param readCount the readCount to set
	 */
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	/**
	 * @return the tagId
	 */
	public Long getTagId() {
		return tagId;
	}
	/**
	 * @param tagId the tagId to set
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	/**
	 * @return the tags
	 */
	public List<TagInfo> getTagInfos() {
		return tagInfos;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTagInfos(List<TagInfo> tags) {
		this.tagInfos = tags;
	}
	/**
	 * @return the commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}
	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * @return the image2
	 */
	public String getImage2() {
		return image2;
	}
	/**
	 * @param image2 the image2 to set
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}
    /**
     * @return the enableDate
     */
    public Date getEnableDate() {
        return enableDate;
    }
    /**
     * @param enableDate the enableDate to set
     */
    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }
    /**
     * @return the business
     */
    public Boolean getBusiness() {
        return business;
    }
    /**
     * @param business the business to set
     */
    public void setBusiness(Boolean business) {
        this.business = business;
    }

}
