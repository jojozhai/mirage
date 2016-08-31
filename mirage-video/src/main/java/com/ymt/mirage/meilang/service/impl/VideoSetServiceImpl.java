/**
 * 
 */
package com.ymt.mirage.meilang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.meilang.domain.VideoSet;
import com.ymt.mirage.meilang.domain.Videos;
import com.ymt.mirage.meilang.dto.VideoInfo;
import com.ymt.mirage.meilang.dto.VideoSetInfo;
import com.ymt.mirage.meilang.repository.VideoSetRepository;
import com.ymt.mirage.meilang.repository.spec.VideoSetSpec;
import com.ymt.mirage.meilang.service.VideoSetService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Service("videoSetService")
@Transactional
public class VideoSetServiceImpl implements VideoSetService {

	@Autowired
	private VideoSetRepository videoSetRepository;
	
	@Override
	public Page<VideoSetInfo> query(VideoSetInfo videoSetInfo, Pageable pageable) {
		Page<VideoSet> pageData = videoSetRepository.findAll(new VideoSetSpec(videoSetInfo), pageable);
		return QueryResultConverter.convert(pageData, VideoSetInfo.class, pageable);
	}

	@Override
	public VideoSetInfo create(VideoSetInfo videoSetInfo) {
		VideoSet videoSet = new VideoSet();
		BeanUtils.copyProperties(videoSetInfo, videoSet);
		videoSetInfo.setId(videoSetRepository.save(videoSet).getId());
		return videoSetInfo;
	}

	@Override
	public VideoSetInfo getInfo(Long id) {
		VideoSet videoSet = videoSetRepository.findOne(id);
		VideoSetInfo info = new VideoSetInfo();
		BeanUtils.copyProperties(videoSet, info);
		List<VideoInfo> infos = new ArrayList<VideoInfo>();
		for (Videos videos : videoSet.getVideos()) {
			VideoInfo videoInfo = new VideoInfo();
			BeanUtils.copyProperties(videos.getVideo(), videoInfo);
			infos.add(videoInfo);
		}
		info.setVideoInfos(infos);
		return info;
	}

	@Override
	public VideoSetInfo update(VideoSetInfo videoSetInfo) {
		VideoSet videoSet = videoSetRepository.findOne(videoSetInfo.getId());
		BeanUtils.copyProperties(videoSetInfo, videoSet);
		videoSetRepository.save(videoSet);
		return videoSetInfo;
	}

	@Override
	public void delete(Long id) {
		videoSetRepository.delete(id);		
	}

	@Override
	public List<VideoSetInfo> findAll() {
		List<VideoSet> sets = videoSetRepository.findAll(new Sort(Direction.DESC, "createdTime"));
		return QueryResultConverter.convert(sets, VideoSetInfo.class);
	}

}
