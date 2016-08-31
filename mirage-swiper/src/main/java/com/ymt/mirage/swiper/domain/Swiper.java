/**
 * 
 */
package com.ymt.mirage.swiper.domain;

import javax.persistence.Entity;

import com.ymt.pz365.data.jpa.domain.SortableImpl;

/**
 * @author zhailiang
 * @since 2016年5月23日
 */
@Entity
public class Swiper extends SortableImpl {

	private String name;
	private boolean enable;
	private String image;
	private String link;
	private String type;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the location
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param location the location to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}
	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
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
	
}
