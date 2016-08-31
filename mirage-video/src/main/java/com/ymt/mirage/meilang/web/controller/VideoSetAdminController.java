/**
 * 
 */
package com.ymt.mirage.meilang.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.meilang.dto.VideoSetInfo;
import com.ymt.mirage.meilang.service.VideoSetService;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@RestController
@Profile("admin")
public class VideoSetAdminController {
	
	@Autowired
	private VideoSetService videoSetService;

	@RequestMapping(value = "/videoSet", method = RequestMethod.POST)
	public VideoSetInfo create(@RequestBody VideoSetInfo videoSetInfo) {
		return videoSetService.create(videoSetInfo);
	}

	@RequestMapping(value = "/videoSet", method = RequestMethod.GET)
	public Page<VideoSetInfo> query(VideoSetInfo videoSetInfo, Pageable pageable) {
		return videoSetService.query(videoSetInfo, pageable);
	}
	
	@RequestMapping(value = "/videoSet/all", method = RequestMethod.GET)
	public List<VideoSetInfo> findAll() {
		return videoSetService.findAll();
	}

	@RequestMapping(value = "/videoSet/{id}", method = RequestMethod.GET)
	public VideoSetInfo getInfo(@PathVariable Long id) {
		return videoSetService.getInfo(id);
	}

	@RequestMapping(value = "/videoSet/{id}", method = RequestMethod.PUT)
	public VideoSetInfo update(@RequestBody VideoSetInfo videoSetInfo) {
		return videoSetService.update(videoSetInfo);
	}

	@RequestMapping(value = "/videoSet/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		videoSetService.delete(id);
	}
	
}
