/**
 * 
 */
package com.ymt.mirage.social.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
public class CommentInfo {
	
    /**
     * 
     */
    private List<CommentInfo> replys = new ArrayList<CommentInfo>();
	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private Date createdTime = new Date();
	/**
	 * 
	 */
	private String target;
	/**
	 * 
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
	 * 回复目标人的Id
	 */
	private Long replyToId;
	/**
	 * 回复目标人的名称
	 */
	private String replyToName;
	/**
	 * 
	 */
	private String content;
	/**
     * 赞的总数
     */
    private int praiseCount;
    /**
     * 是否赞过
     */
    private boolean praised;
    /**
     * 
     */
    private Long userId;
    /**
     * 
     */
    private boolean withReply;
    
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
     * @return the replys
     */
    public List<CommentInfo> getReplys() {
        return replys;
    }
    /**
     * @param replys the replys to set
     */
    public void setReplys(List<CommentInfo> replys) {
        this.replys = replys;
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
     * @return the withReply
     */
    public boolean isWithReply() {
        return withReply;
    }
    /**
     * @param withReply the withReply to set
     */
    public void setWithReply(boolean withReply) {
        this.withReply = withReply;
    }
    /**
     * @return the praised
     */
    public boolean isPraised() {
        return praised;
    }
    /**
     * @param praised the praised to set
     */
    public void setPraised(boolean praised) {
        this.praised = praised;
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
