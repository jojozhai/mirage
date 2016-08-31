/**
 * 
 */
package com.ymt.mirage.swiper.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.swiper.dto.SwiperInfo;
import com.ymt.mirage.swiper.service.SwiperService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("weixin")
public class SwiperWeixinController {
	
	@Autowired
	private SwiperService swiperService;

	@RequestMapping(value = "/swiper", method = RequestMethod.GET)
	public Page<SwiperInfo> query(SwiperInfo swiperInfo, Pageable pageable) {
		return swiperService.query(swiperInfo, pageable);
	}
	
}
