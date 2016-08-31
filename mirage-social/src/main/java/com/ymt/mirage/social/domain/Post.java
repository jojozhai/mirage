/**
 * 
 */
package com.ymt.mirage.social.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

import com.ymt.mirage.social.dto.Socialable;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Entity
public class Post extends DomainImpl implements Socialable {
	
	public static final String CONTAINER_TYPE_CHALLENGE = "challenge";
	
	public static final String USER_TYPE_USER = "user";
	public static final String USER_TYPE_TEACHER = "teacher";
	
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
	@Lob
	private String images;
	/**
	 * post的内容
	 */
	@Lob
	private String content;
	/**
	 * 赞
	 */
	@Lob
	private String praise = "[]";
	/**
	 * 评论
	 */
	@Lob
	private String comment = "[]";
	/**
	 * 赞的总数
	 */
	private int praiseCount;
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
	 * @return the praise
	 */
	public String getPraise() {
		return praise;
	}
	/**
	 * @param praise the praise to set
	 */
	public void setPraise(String praise) {
		this.praise = praise;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
    /**
     * @return the praiseCount
     */
    public int getPraiseCount() {
        return praiseCount;
    }
    /**
     * @param praiseCount the praiseCount to set
     */
    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }
	
}
