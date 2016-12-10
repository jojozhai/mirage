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

    private String desc;
    
    private int hot;
    
    private int hotplus;
    
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

    /**
     * @return the hotplus
     */
    public int getHotplus() {
        return hotplus;
    }

    /**
     * @param hotplus the hotplus to set
     */
    public void setHotplus(int hotplus) {
        this.hotplus = hotplus;
    }


}
