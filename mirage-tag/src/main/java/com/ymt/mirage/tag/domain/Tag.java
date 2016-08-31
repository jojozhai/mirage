/**
 * 
 */
package com.ymt.mirage.tag.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.BeanUtils;

import com.ymt.mirage.tag.dto.TagInfo;
import com.ymt.pz365.data.jpa.domain.TreeImpl;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Entity
public class Tag extends TreeImpl<Tag> {
	
	/**
	 * 
	 */
	private String name;
	
	private String image;

	public TagInfo toTree() {
		TagInfo result = new TagInfo();
		BeanUtils.copyProperties(this, result);
		List<TagInfo> children = new ArrayList<TagInfo>();
		for (Tag child : getChilds()) {
			children.add(child.toTree());
		}
		result.setChildren(children);
		return result;
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


}
