/**
 * 
 */
package com.ymt.mirage.meilang.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.meilang.dto.VideosInfo;
import com.ymt.mirage.meilang.service.VideosService;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@RestController
@Profile("admin")
public class VideosAdminController {
	
	@Autowired
	private VideosService videosService;

	@RequestMapping(value = "/videos", method = RequestMethod.GET)
	public List<VideosInfo> query(VideosInfo videosInfo) {
		return videosService.query(videosInfo);
	}

	@RequestMapping(value = "/videos/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		videosService.delete(id);
	}
	
	@RequestMapping(value = "/videos", method = RequestMethod.PUT)
	public void update(@RequestBody List<VideosInfo> infos) {
		videosService.update(infos);
	}
}
