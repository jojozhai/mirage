/**
 * 
 */
package com.ymt.mirage.challenge.dto;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public class TeacherInfo {

	private Long id;
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
	
}
