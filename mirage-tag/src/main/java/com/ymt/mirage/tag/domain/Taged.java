/**
 * 
 */
package com.ymt.mirage.tag.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Entity
public class Taged extends DomainImpl {
	
	private String target;
	private Long targetId;
	@ManyToOne
	private Tag tag;
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
	 * @return the tag
	 */
	public Tag getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
