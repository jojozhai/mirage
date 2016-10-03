/**
 * 
 */
package com.ymt.mirage.clearing.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.clearing.dto.WithdrawalsInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface WithdrawalsService {

	Page<WithdrawalsInfo> query(WithdrawalsInfo withdrawalsInfo, Pageable pageable);
	
	WithdrawalsInfo create(WithdrawalsInfo withdrawalsInfo);

	WithdrawalsInfo getInfo(Long id);

	WithdrawalsInfo update(WithdrawalsInfo withdrawalsInfo) throws Exception;

	void delete(Long id);
	
}
