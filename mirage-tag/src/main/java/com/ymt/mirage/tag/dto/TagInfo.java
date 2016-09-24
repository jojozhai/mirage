/**
 * 
 */
package com.ymt.mirage.tag.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public class TagInfo {
	
	private Long id;

	private Long parentId;
	
	private String parentName;
	
	private String name;
	
	private String desc;
	
	private int hot;
	
	private String image;
	/**
	 * 子节点
	 */
	private List<TagInfo> children = new ArrayList<TagInfo>();
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
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the children
	 */
	public List<TagInfo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TagInfo> children) {
		this.children = children;
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
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}
	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
     * @return the hot
     */
    public int getHot() {
        return hot;
    }
    /**
     * @param hot the hot to set
     */
    public void setHot(int hot) {
        this.hot = hot;
    }

}
