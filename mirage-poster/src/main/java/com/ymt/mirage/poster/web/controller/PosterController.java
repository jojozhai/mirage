/**
 * 
 */
package com.ymt.mirage.poster.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.poster.dto.PosterInfo;
import com.ymt.mirage.poster.service.PosterService;
import com.ymt.pz365.framework.param.dto.ParamInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class PosterController {
	
	@Autowired
	private PosterService posterService;

	@RequestMapping(value = "/poster", method = RequestMethod.POST)
	public PosterInfo create(@RequestBody PosterInfo posterInfo) {
		return posterService.create(posterInfo);
	}

	@RequestMapping(value = "/poster", method = RequestMethod.GET)
	public Page<PosterInfo> query(PosterInfo posterInfo, Pageable pageable) {
		return posterService.query(posterInfo, pageable);
	}
	
	@RequestMapping(value = "/poster/desc", method = RequestMethod.POST)
	public void saveActivityDesc(@RequestBody ParamInfo paramInfo) {
		posterService.saveActivityDesc(paramInfo);
	}

	@RequestMapping(value = "/poster/desc", method = RequestMethod.GET)
	public ParamInfo getActivityDesc() {
		return posterService.getActivityDesc();
	}

	@RequestMapping(value = "/poster/{id}", method = RequestMethod.GET)
	public PosterInfo getInfo(@PathVariable Long id) {
		return posterService.getInfo(id);
	}

	@RequestMapping(value = "/poster/{id}", method = RequestMethod.PUT)
	public PosterInfo update(@RequestBody PosterInfo posterInfo) {
		return posterService.update(posterInfo);
	}

	@RequestMapping(value = "/poster/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		posterService.delete(id);
	}
}
