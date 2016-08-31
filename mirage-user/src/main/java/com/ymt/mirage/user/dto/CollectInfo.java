/**
 * 
 */
package com.ymt.mirage.user.dto;

/**
 * @author zhailiang
 * @since 2016年5月21日
 */
public class CollectInfo {
	
	private Long id;
	private Long userId;
	private Long targetId;
	private String target;
	private boolean collect;
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
	 * @return the collect
	 */
	public boolean isCollect() {
		return collect;
	}
	/**
	 * @param collect the collect to set
	 */
	public void setCollect(boolean collect) {
		this.collect = collect;
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

}
