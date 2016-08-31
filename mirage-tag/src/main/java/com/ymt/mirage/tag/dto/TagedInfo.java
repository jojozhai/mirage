/**
 * 
 */
package com.ymt.mirage.tag.dto;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public class TagedInfo {

	private String target;
	private Long targetId;
	
	public TagedInfo() {
		
	}
	
	public TagedInfo(String target, Long targetId) {
		this.target = target;
		this.targetId = targetId;
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
	
}
