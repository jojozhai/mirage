/**
 * 
 */
package com.ymt.mirage.apply.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.apply.dto.ApplyInfo;
import com.ymt.mirage.apply.service.ApplyService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;

	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public ApplyInfo create(@RequestBody ApplyInfo applyInfo) {
		return applyService.create(applyInfo);
	}

	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public Page<ApplyInfo> query(ApplyInfo applyInfo, Pageable pageable) {
		return applyService.query(applyInfo, pageable);
	}
	
	@RequestMapping(value = "/apply/{id}", method = RequestMethod.GET)
	public ApplyInfo getInfo(@PathVariable Long id) {
		return applyService.getInfo(id);
	}

	@RequestMapping(value = "/apply/{id}", method = RequestMethod.PUT)
	public ApplyInfo update(@RequestBody ApplyInfo applyInfo) {
		return applyService.update(applyInfo);
	}

	@RequestMapping(value = "/apply/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		applyService.delete(id);
	}
}
