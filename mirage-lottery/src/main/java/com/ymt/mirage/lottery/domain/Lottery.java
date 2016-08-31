/**
 * 
 */
package com.ymt.mirage.lottery.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
@Entity
public class Lottery extends DomainImpl {
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 过期时间
	 */
	@Temporal(TemporalType.DATE)
	private Date expiredDate;
	/**
	 * 
	 */
	@ElementCollection
	private List<Prize> prizes;
	/**
	 * 活动说明
	 */
	@Lob
	private String desc;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the expiredDate
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}
	/**
	 * @param expiredDate the expiredDate to set
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	/**
	 * @return the prizes
	 */
	public List<Prize> getPrizes() {
		return prizes;
	}
	/**
	 * @param prizes the prizes to set
	 */
	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
