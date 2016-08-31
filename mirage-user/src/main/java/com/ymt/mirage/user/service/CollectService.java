/**
 * 
 */
package com.ymt.mirage.user.service;

import com.ymt.mirage.user.dto.CollectInfo;

/**
 * @author zhailiang
 * @since 2016年5月21日
 */
public interface CollectService {
	
	void collect(CollectInfo collectInfo) throws Exception;

	boolean isCollect(CollectInfo collectInfo) throws Exception;

}
