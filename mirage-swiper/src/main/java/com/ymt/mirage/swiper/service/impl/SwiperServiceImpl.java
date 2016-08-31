/**
 * 
 */
package com.ymt.mirage.swiper.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.swiper.domain.Swiper;
import com.ymt.mirage.swiper.dto.SwiperInfo;
import com.ymt.mirage.swiper.repository.SwiperRepository;
import com.ymt.mirage.swiper.repository.spec.SwiperSpec;
import com.ymt.mirage.swiper.service.SwiperService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月23日
 */
@Service("swiperService")
@Transactional
public class SwiperServiceImpl implements SwiperService {

	@Autowired
	private SwiperRepository swiperRepository;
	
	@Override
	public Page<SwiperInfo> query(SwiperInfo swiperInfo, Pageable pageable) {
		Page<Swiper> pageData = swiperRepository.findAll(new SwiperSpec(swiperInfo), pageable);
		return QueryResultConverter.convert(pageData, SwiperInfo.class, pageable);
	}

	@Override
	public SwiperInfo create(SwiperInfo swiperInfo) {
		Swiper swiper = new Swiper();
		BeanUtils.copyProperties(swiperInfo, swiper);
		swiperInfo.setId(swiperRepository.save(swiper).getId());
		return swiperInfo;
	}

	@Override
	public SwiperInfo getInfo(Long id) {
		Swiper swiper = swiperRepository.findOne(id);
		SwiperInfo info = new SwiperInfo();
		BeanUtils.copyProperties(swiper, info);
		return info;
	}

	@Override
	public SwiperInfo update(SwiperInfo swiperInfo) {
		Swiper swiper = swiperRepository.findOne(swiperInfo.getId());
		BeanUtils.copyProperties(swiperInfo, swiper);
		swiperRepository.save(swiper);
		return swiperInfo;
	}

	@Override
	public void delete(Long id) {
		swiperRepository.delete(id);		
	}

}
