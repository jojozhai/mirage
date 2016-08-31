/**
 * 
 */
package com.ymt.mirage.social.domain;

import javax.persistence.Entity;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@Entity
public class Praise extends DomainImpl {

	/**
	 * 赞的人
	 */
	private Long createrId;
	/**
	 * 被赞的对象的id
	 */
	private Long targetId;
	/**
	 * 发送人姓名
	 */
	private String createrName;
	
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
	
}
