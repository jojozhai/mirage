/**
 * 
 */
package com.ymt.mirage.tag.domain;

import com.ymt.pz365.data.jpa.domain.Domain;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
public interface TagRelation<T> extends Domain {

	void setTarget(T domain);

	void setTag(Tag one);

	Tag getTag();

}
