/**
 * 
 */
package com.ymt.mirage.user.web.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.user.dto.LevelInfo;
import com.ymt.mirage.user.service.LevelService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class LevelController {
	
	@Autowired
	private LevelService levelService;

	@RequestMapping(value = "/level", method = RequestMethod.POST)
	public LevelInfo create(@RequestBody LevelInfo levelInfo) {
		return levelService.create(levelInfo);
	}

	@RequestMapping(value = "/level", method = RequestMethod.GET)
	public Page<LevelInfo> query(LevelInfo levelInfo, Pageable pageable) {
		return levelService.query(levelInfo, pageable);
	}
	
	@RequestMapping(value = "/level/{id}", method = RequestMethod.GET)
	public LevelInfo getInfo(@PathVariable Long id) {
		return levelService.getInfo(id);
	}

	@RequestMapping(value = "/level/{id}", method = RequestMethod.PUT)
	public LevelInfo update(@RequestBody LevelInfo levelInfo) {
		return levelService.update(levelInfo);
	}

	@RequestMapping(value = "/level/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		levelService.delete(id);
	}
}
