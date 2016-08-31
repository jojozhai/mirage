/**
 * 
 */
package com.ymt.mirage.swiper.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.swiper.dto.SwiperInfo;

/**
 * @author zhailiang
 * @since 2016年5月23日
 */
public interface SwiperService {
	
	Page<SwiperInfo> query(SwiperInfo swiperInfo, Pageable pageable);
	
	SwiperInfo create(SwiperInfo swiperInfo);

	SwiperInfo getInfo(Long id);

	SwiperInfo update(SwiperInfo swiperInfo);

	void delete(Long id);

}
