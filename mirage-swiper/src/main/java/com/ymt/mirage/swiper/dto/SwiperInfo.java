/**
 * 
 */
package com.ymt.mirage.swiper.dto;

/**
 * @author zhailiang
 * @since 2016年5月23日
 */
public class SwiperInfo {
	
	private Long id;
	private String name;
	private Integer index;
	private Boolean enable;
	private String image;
	private String link;
	private String type;
	
	
	/**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }
    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
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
	 * @return the enable
	 */
	public Boolean getEnable() {
		return enable;
	}
	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
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
