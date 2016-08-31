/**
 * 
 */
package com.ymt.mirage.swiper.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.swiper.dto.SwiperInfo;
import com.ymt.mirage.swiper.service.SwiperService;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@RestController
@Profile("admin")
public class SwiperAdminController {
	
	@Autowired
	private SwiperService swiperService;

	@RequestMapping(value = "/swiper", method = RequestMethod.POST)
	public SwiperInfo create(@RequestBody SwiperInfo swiperInfo) {
		return swiperService.create(swiperInfo);
	}

	@RequestMapping(value = "/swiper", method = RequestMethod.GET)
	public Page<SwiperInfo> query(SwiperInfo swiperInfo, Pageable pageable) {
		return swiperService.query(swiperInfo, pageable);
	}

	@RequestMapping(value = "/swiper/{id}", method = RequestMethod.GET)
	public SwiperInfo getInfo(@PathVariable Long id) throws Exception {
		return swiperService.getInfo(id);
	}

	@RequestMapping(value = "/swiper/{id}", method = RequestMethod.PUT)
	public SwiperInfo update(@RequestBody SwiperInfo swiperInfo) {
		return swiperService.update(swiperInfo);
	}

	@RequestMapping(value = "/swiper/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		swiperService.delete(id);
	}

}
