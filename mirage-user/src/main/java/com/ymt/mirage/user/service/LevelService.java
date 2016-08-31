/**
 * 
 */
package com.ymt.mirage.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.user.dto.LevelInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface LevelService {

	Page<LevelInfo> query(LevelInfo levelInfo, Pageable pageable);
	
	LevelInfo create(LevelInfo levelInfo);

	LevelInfo getInfo(Long id);

	LevelInfo update(LevelInfo levelInfo);

	void delete(Long id);

}
