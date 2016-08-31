/**
 * 
 */
package com.ymt.mirage.article.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.ymt.mirage.social.dto.Commentable;
import com.ymt.mirage.tag.domain.Tagable;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@Entity
public class Article extends DomainImpl implements Tagable, Commentable {
	
	/**
	 * 发布
	 */
	private boolean enable;
	/**
	 * 商业推文
	 */
	private boolean business;
	/**
	 * 发布日期
	 */
	private Date enableDate;
	/**
	 * 是否发布过，如果发布过，然后人工下线了，不再自动发布。
	 */
	private boolean enabled;
	/**
	 * 置顶
	 */
	private boolean top;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	@Lob
	private String content;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 图片
	 */
	private String image2;
	/**
	 * 阅读量
	 */
	private int readCount;
	/**
	 * 负责人
	 */
	private String principal;
	/**
	 * 评论
	 */
	@Lob
	private String comment;
	/**
	 * 
	 */
	@OneToMany(mappedBy = "target", cascade = CascadeType.REMOVE)
	private List<ArticleTag> tags = new ArrayList<ArticleTag>();
	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}
	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	/**
	 * @return the top
	 */
	public boolean isTop() {
		return top;
	}
	/**
	 * @param top the top to set
	 */
	public void setTop(boolean top) {
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
	 * @return the tags
	 */
	public List<ArticleTag> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<ArticleTag> tags) {
		this.tags = tags;
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
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }
    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    /**
     * @return the business
     */
    public boolean isBusiness() {
        return business;
    }
    /**
     * @param business the business to set
     */
    public void setBusiness(boolean business) {
        this.business = business;
    }
	
}
