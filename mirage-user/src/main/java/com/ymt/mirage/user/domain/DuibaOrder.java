/**
 * 
 */
package com.ymt.mirage.user.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 *
 */
@Entity
public class DuibaOrder extends DomainImpl {
	
	@ManyToOne
	private User user;
	
	/**
	 * 消耗积分数
	 */
	private Long credits;
	
	/**
	 * 实际扣款，分为单位
	 */
	private Integer actualPrice;
	
	/**
	 * 兑吧订单号
	 */
	private String orderNum;
	
	/**
	 * 订单状态
	 */
	@Enumerated(EnumType.STRING)
	private DuibaOrderStatus orderStatus;
	/**
	 * 积分状态
	 */
	@Enumerated(EnumType.STRING)
	private DuibaPointStatus pointStatus;
	
	/**
	 * 类型：QB,Phonebill,Alipay,Coupon  所有类型不区分大小写
	 */
	private String type;
	/**
	 * 描述
	 */
	private String description;
	
	@Version
	private int version;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the credits
	 */
	public Long getCredits() {
		return credits;
	}

	/**
	 * @param credits the credits to set
	 */
	public void setCredits(Long credits) {
		this.credits = credits;
	}

	/**
	 * @return the actualPrice
	 */
	public Integer getActualPrice() {
		return actualPrice;
	}

	/**
	 * @param actualPrice the actualPrice to set
	 */
	public void setActualPrice(Integer actualPrice) {
		this.actualPrice = actualPrice;
	}

	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the status
	 */
	public DuibaOrderStatus getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param status the status to set
	 */
	public void setOrderStatus(DuibaOrderStatus status) {
		this.orderStatus = status;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the pointStatus
	 */
	public DuibaPointStatus getPointStatus() {
		return pointStatus;
	}

	/**
	 * @param pointStatus the pointStatus to set
	 */
	public void setPointStatus(DuibaPointStatus pointStatus) {
		this.pointStatus = pointStatus;
	}
	

}
