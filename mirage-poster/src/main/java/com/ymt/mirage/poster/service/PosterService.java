/**
 * 
 */
package com.ymt.mirage.poster.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.poster.dto.PosterInfo;
import com.ymt.pz365.framework.param.dto.ParamInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface PosterService {

	Page<PosterInfo> query(PosterInfo posterInfo, Pageable pageable);
	
	PosterInfo create(PosterInfo posterInfo);

	PosterInfo getInfo(Long id);

	PosterInfo update(PosterInfo posterInfo);

	void delete(Long id);

	void saveActivityDesc(ParamInfo paramInfo);

	ParamInfo getActivityDesc();

}
