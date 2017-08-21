/**
 * 
 */
package com.ymt.mirage.apply.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@Entity
public class Apply extends PendingItem {
	
	/**
	 * 申请的目标，比如加入社群，成为参与者等
	 */
	private String target;
	/**
	 * 申请目标的id
	 */
	private Long targetId;
	/**
	 * 申请状态
	 */
	@Enumerated(EnumType.STRING)
	private ApplyStatus status;
	/**
	 * 处理时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;
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
	 * @return the status
	 */
	public ApplyStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ApplyStatus status) {
		this.status = status;
	}
	/**
	 * @return the processTime
	 */
	public Date getProcessTime() {
		return processTime;
	}
	/**
	 * @param processTime the processTime to set
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

}
