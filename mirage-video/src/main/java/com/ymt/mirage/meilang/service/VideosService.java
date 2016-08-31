/**
 * 
 */
package com.ymt.mirage.meilang.service;

import java.util.List;

import com.ymt.mirage.meilang.dto.VideosInfo;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public interface VideosService {

	List<VideosInfo> query(VideosInfo videosInfo);

	void delete(Long id);

	void update(List<VideosInfo> infos);

}
