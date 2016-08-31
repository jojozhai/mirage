/**
 * 
 */
package com.ymt.mirage.meilang.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.meilang.dto.VideoInfo;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public interface VideoService {
	
	Page<VideoInfo> query(VideoInfo videoInfo, Pageable pageable);
	
	VideoInfo create(VideoInfo videoInfo);

	VideoInfo getInfo(Long id);
	
	VideoInfo getInfo(Long id, Long userId);

	VideoInfo update(VideoInfo videoInfo);

	void delete(Long id);

	

}
