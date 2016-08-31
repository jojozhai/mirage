/**
 * 
 */
package com.ymt.mirage.tag.domain;

import java.util.List;

import com.ymt.pz365.data.jpa.domain.Domain;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
public interface Tagable extends Domain {

	@SuppressWarnings("rawtypes")
	List getTags();

}
