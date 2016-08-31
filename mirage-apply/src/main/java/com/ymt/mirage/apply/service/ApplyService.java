/**
 * 
 */
package com.ymt.mirage.apply.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.apply.dto.ApplyInfo;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
public interface ApplyService {
	
	Page<ApplyInfo> query(ApplyInfo applyInfo, Pageable pageable);
	
	ApplyInfo create(ApplyInfo applyInfo);

	ApplyInfo getInfo(Long id);

	ApplyInfo update(ApplyInfo applyInfo);

	void delete(Long id);

}
