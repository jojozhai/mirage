/**
 * 
 */
package com.ymt.mirage.poster.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.poster.domain.Poster;
import com.ymt.mirage.poster.dto.PosterInfo;
import com.ymt.mirage.poster.repository.PosterRepository;
import com.ymt.mirage.poster.repository.spec.PosterSpec;
import com.ymt.mirage.poster.service.PosterService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.param.dto.ParamInfo;
import com.ymt.pz365.framework.param.service.ParamService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Service("posterService")
@Transactional
public class PosterServiceImpl implements PosterService {
	
	@Autowired
	private PosterRepository posterRepository;
	
	@Autowired
	private ParamService paramService;
	
	@Override
	public Page<PosterInfo> query(PosterInfo posterInfo, Pageable pageable) {
		Page<Poster> pageData = posterRepository.findAll(new PosterSpec(posterInfo), pageable);
		return QueryResultConverter.convert(pageData, PosterInfo.class, pageable);
	}

	@Override
	public PosterInfo create(PosterInfo posterInfo) {
		Poster poster = new Poster();
		BeanUtils.copyProperties(posterInfo, poster);
		posterInfo.setId(posterRepository.save(poster).getId());
		return posterInfo;
	}

	@Override
	public PosterInfo getInfo(Long id) {
		Poster poster = posterRepository.findOne(id);
		PosterInfo info = new PosterInfo();
		BeanUtils.copyProperties(poster, info);
		return info;
	}

	@Override
	public PosterInfo update(PosterInfo posterInfo) {
		Poster poster = posterRepository.findOne(posterInfo.getId());
		BeanUtils.copyProperties(posterInfo, poster);
		posterRepository.save(poster);
		return posterInfo;
	}

	@Override
	public void delete(Long id) {
		posterRepository.delete(id);		
	}

	@Override
	public void saveActivityDesc(ParamInfo paramInfo) {
		paramService.update(paramInfo);
	}

	@Override
	public ParamInfo getActivityDesc() {
		return paramService.getParam(ParamService.PARAM_POSTER_ACTIVITY_DESC, "");
	}

}
