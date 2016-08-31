/**
 * 
 */
package com.ymt.mirage.user.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.user.domain.Level;
import com.ymt.mirage.user.dto.LevelInfo;
import com.ymt.mirage.user.repository.LevelRepository;
import com.ymt.mirage.user.repository.impl.LevelSpec;
import com.ymt.mirage.user.service.LevelService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Service("levelService")
@Transactional
public class LevelServiceImpl implements LevelService {
	
	@Autowired
	private LevelRepository levelRepository;
	
	@Override
	public Page<LevelInfo> query(LevelInfo levelInfo, Pageable pageable) {
		Page<Level> pageData = levelRepository.findAll(new LevelSpec(levelInfo), pageable);
		return QueryResultConverter.convert(pageData, LevelInfo.class, pageable);
	}

	@Override
	public LevelInfo create(LevelInfo levelInfo) {
		Level level = new Level();
		BeanUtils.copyProperties(levelInfo, level);
		levelInfo.setId(levelRepository.save(level).getId());
		return levelInfo;
	}

	@Override
	public LevelInfo getInfo(Long id) {
		Level level = levelRepository.findOne(id);
		LevelInfo info = new LevelInfo();
		BeanUtils.copyProperties(level, info);
		return info;
	}

	@Override
	public LevelInfo update(LevelInfo levelInfo) {
		Level level = levelRepository.findOne(levelInfo.getId());
		BeanUtils.copyProperties(levelInfo, level);
		levelRepository.save(level);
		return levelInfo;
	}

	@Override
	public void delete(Long id) {
		levelRepository.delete(id);		
	}

}
