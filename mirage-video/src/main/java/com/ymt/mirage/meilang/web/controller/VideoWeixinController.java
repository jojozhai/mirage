/**
 * 
 */
package com.ymt.mirage.meilang.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.meilang.dto.VideoInfo;
import com.ymt.mirage.meilang.service.VideoService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@RestController
@Profile({"weixin", "app"})
public class VideoWeixinController {
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public Page<VideoInfo> query(VideoInfo videoInfo, Pageable pageable) {
		return videoService.query(videoInfo, pageable);
	}
	
	@RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
	public VideoInfo getInfo(@PathVariable Long id) {
		return videoService.getInfo(id, CurrentUserHolder.getCurrentUserId());
	}

}
