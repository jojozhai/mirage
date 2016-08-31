/**
 * 
 */
package com.ymt.mirage.challenge.domain;

import javax.persistence.Entity;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Entity
public class Teacher extends DomainImpl {

	/**
	 * 老师名称
	 */
	private String name;
	/**
	 * 描述 
	 */
	private String desc;
	/**
	 * 头像 
	 */
	private String image;

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
	 * @return the headimgurl
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param headimgurl the headimgurl to set
	 */
	public void setImage(String headimgurl) {
		this.image = headimgurl;
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
