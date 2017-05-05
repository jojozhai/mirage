/**
 * 
 */
package com.ymt.mirage.meilang.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.meilang.domain.Video;
import com.ymt.mirage.meilang.domain.VideoSet;
import com.ymt.mirage.meilang.domain.Videos;
import com.ymt.mirage.meilang.dto.VideoInfo;
import com.ymt.mirage.meilang.dto.VideoSetInfo;
import com.ymt.mirage.meilang.repository.VideoRepository;
import com.ymt.mirage.meilang.repository.VideoSetRepository;
import com.ymt.mirage.meilang.repository.VideosRepository;
import com.ymt.mirage.meilang.repository.spec.VideoSpec;
import com.ymt.mirage.meilang.service.VideoService;
import com.ymt.mirage.tag.dto.TagedInfo;
import com.ymt.mirage.tag.service.TagService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.aliyun.oss.OssFileService;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private VideosRepository videosRepository;
	
	@Autowired
	private VideoSetRepository videoSetRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OssFileService ossFileService;
	
	@Override
	public Page<VideoInfo> query(VideoInfo videoInfo, Pageable pageable) {
		Page<Video> pageData = videoRepository.findAll(new VideoSpec(videoInfo), pageable);
		return QueryResultConverter.convert(pageData, VideoInfo.class, pageable);
	}

	@Override
	public VideoInfo create(VideoInfo videoInfo) {
		Video video = new Video();
		BeanUtils.copyProperties(videoInfo, video);
		videoInfo.setId(videoRepository.save(video).getId());
		tagService.addTag(Video.class.getName(), video.getId(), videoInfo.getTags());
		addVideos(video, videoInfo.getSetInfos());
		return videoInfo;
	}

	private void addVideos(Video video, List<VideoSetInfo> sets) {
		if(CollectionUtils.isNotEmpty(sets)){
			int index = 0;
			for (VideoSetInfo setInfo : sets) {
				Videos videos = new Videos();
				videos.setVideo(video);
				videos.setSet(videoSetRepository.getOne(setInfo.getId()));
				videos.setIndex(index++);
				videosRepository.save(videos);
			}
		}
	}

	@Override
	public VideoInfo getInfo(Long id) {
		Video video = videoRepository.findOne(id);
		VideoInfo info = new VideoInfo();
		BeanUtils.copyProperties(video, info);
		info.setTags(tagService.getTags(new TagedInfo(Video.class.getName(), video.getId())));
		info.setSetInfos(getSetInfos(video.getSets()));
		return info;
	}

	@Override
	public VideoInfo getInfo(Long id, Long userId) {
		VideoInfo info = new VideoInfo();
		Video video = videoRepository.findOne(id);
		if(StringUtils.isBlank(video.getPlayUrl()) || 
				new DateTime().isAfter(video.getPlayUrlExpire().getTime())){
			Date expiredTime = new DateTime().plusHours(1).toDate();
			String playUrl = ossFileService.getSignaturedVideoUrl(video.getUrl(), expiredTime);
			video.setPlayUrl(playUrl);
			video.setPlayUrlExpire(expiredTime);
		}

		BeanUtils.copyProperties(video, info);
		
		info.setHasPermission(hasPermission(video.getId(), userId));
		return info;
	}
	
	private boolean hasPermission(Long videoId, Long userId) {
		Video video = videoRepository.findOne(videoId);
		if(video.getPrice().compareTo(BigDecimal.ZERO) == 1){
			if(userId == null){
				return false;
			}else{
				User user = userRepository.findOne(userId);
				if(user.isVipValidation()) {
					return true;
				}else{
					return isVideoBuyer(videoId, userId) || isVideoSetBuyer(videoId, userId);
				}
			}
		}else{
			return true;
		}
	}

	private boolean isVideoSetBuyer(Long videoId, Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isVideoBuyer(Long videoId, Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	private List<VideoSetInfo> getSetInfos(Set<Videos> sets) {
		List<VideoSetInfo> infos = new ArrayList<VideoSetInfo>();
		for (Videos videos : sets) {
			VideoSetInfo info = new VideoSetInfo();
			VideoSet set = videos.getSet();
			BeanUtils.copyProperties(set, info);
			infos.add(info);
		}
		return infos;
	}

	@Override
	public VideoInfo update(VideoInfo videoInfo) {
		Video video = videoRepository.findOne(videoInfo.getId());
		BeanUtils.copyProperties(videoInfo, video);
		tagService.addTag(Video.class.getName(), video.getId(), videoInfo.getTags());
		videosRepository.delete(videosRepository.findByVideoId(video.getId()));
		addVideos(video, videoInfo.getSetInfos());
		videoRepository.save(video);
		return videoInfo;
	}

	@Override
	public void delete(Long id) {
		videoRepository.delete(id);		
	}

}
