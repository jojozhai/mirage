/**
 * 
 */
package com.ymt.mirage.social.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.vdurmont.emoji.EmojiParser;
import com.ymt.mirage.social.dto.Praiseable;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@Entity
public class Comment extends DomainImpl implements Praiseable {
	
    /**
     * 
     */
    @ManyToOne
    private Comment comment;
	/**
	 * 评论的目标
	 */
	private String target;
	/**
	 * 目标对象的id
	 */
	private Long targetId;
	/**
	 * 发post的人
	 */
	private Long createrId;
	/**
	 * 发送人姓名
	 */
	private String createrName;
	/**
	 * 发送人描述
	 */
	private String createrDesc;
	/**
	 * 发送人头像
	 */
	private String createrHeadimgurl;
	/**
	 * 回复目标条目的Id
	 */
	private Long replyToId;
	/**
	 * 回复的用户id;
	 */
	private Long replyToUserId;
	/**
	 * 回复目标人的名称
	 */
	private String replyToName;
	/**
	 * 内容
	 */
	@Lob
	private String content;
	/**
	 * 赞
	 */
	@Lob
	private String praise;
	/**
	 * 赞的总数
	 */
	private int praiseCount;
	/**
	 * 屏蔽 
	 */
	private boolean disable;
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
	 * @return the replyToId
	 */
	public Long getReplyToId() {
		return replyToId;
	}
	/**
	 * @param replyToId the replyToId to set
	 */
	public void setReplyToId(Long replyToId) {
		this.replyToId = replyToId;
	}
	/**
	 * @return the replyToName
	 */
	public String getReplyToName() {
		return replyToName;
	}
	/**
	 * @param replyToName the replyToName to set
	 */
	public void setReplyToName(String replyToName) {
		this.replyToName = replyToName;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return EmojiParser.parseToUnicode(content);
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = EmojiParser.parseToAliases(content);
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
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * @return the targetId
	 */
	public Long getTargetId() {
		return targetId;
	}
	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
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
    /**
     * @return the replyToUserId
     */
    public Long getReplyToUserId() {
        return replyToUserId;
    }
    /**
     * @param replyToUserId the replyToUserId to set
     */
    public void setReplyToUserId(Long replyToUserId) {
        this.replyToUserId = replyToUserId;
    }
    /**
     * @return the comment
     */
    public Comment getComment() {
        return comment;
    }
    /**
     * @param comment the comment to set
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }
    /**
     * @return the createrDesc
     */
    public String getCreaterDesc() {
        return createrDesc;
    }
    /**
     * @param createrDesc the createrDesc to set
     */
    public void setCreaterDesc(String createrDesc) {
        this.createrDesc = createrDesc;
    }
    /**
     * @return the disable
     */
    public boolean isDisable() {
        return disable;
    }
    /**
     * @param disable the disable to set
     */
    public void setDisable(boolean disable) {
        this.disable = disable;
    }

}
