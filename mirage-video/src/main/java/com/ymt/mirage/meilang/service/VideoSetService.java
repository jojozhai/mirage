/**
 * 
 */
package com.ymt.mirage.meilang.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.meilang.dto.VideoSetInfo;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public interface VideoSetService {
	
	Page<VideoSetInfo> query(VideoSetInfo videoSetInfo, Pageable pageable);
	
	List<VideoSetInfo> findAll();
	
	VideoSetInfo create(VideoSetInfo videoSetInfo);

	VideoSetInfo getInfo(Long id);

	VideoSetInfo update(VideoSetInfo videoSetInfo);

	void delete(Long id);

	

}
