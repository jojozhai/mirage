/**
 * 
 */
package com.ymt.mirage.clearing.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.clearing.dto.RebateConfigInfo;
import com.ymt.mirage.clearing.service.RebateConfigService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class RebateConfigAdminController {
	
	@Autowired
	private RebateConfigService rebateConfigService;

	@RequestMapping(value = "/rebateConfig", method = RequestMethod.POST)
	public RebateConfigInfo create(@RequestBody RebateConfigInfo rebateConfigInfo) {
		return rebateConfigService.create(rebateConfigInfo);
	}

	@RequestMapping(value = "/rebateConfig", method = RequestMethod.GET)
	public Page<RebateConfigInfo> query(RebateConfigInfo rebateConfigInfo, Pageable pageable) {
		return rebateConfigService.query(rebateConfigInfo, pageable);
	}
	
	@RequestMapping(value = "/rebateConfig/{id}", method = RequestMethod.GET)
	public RebateConfigInfo getInfo(@PathVariable Long id) {
		return rebateConfigService.getInfo(id);
	}

	@RequestMapping(value = "/rebateConfig/{id}", method = RequestMethod.PUT)
	public RebateConfigInfo update(@RequestBody RebateConfigInfo rebateConfigInfo) {
		return rebateConfigService.update(rebateConfigInfo);
	}

	@RequestMapping(value = "/rebateConfig/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		rebateConfigService.delete(id);
	}
	
}
