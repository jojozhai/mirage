/**
 * 
 */
package com.ymt.mirage.clearing.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.clearing.dto.ClearingTreeInfo;
import com.ymt.mirage.clearing.service.ClearingTreeService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class ClearingAdminController {
	
	@Autowired
	private ClearingTreeService clearingTreeService;

	@RequestMapping(value = "/clearingTree", method = RequestMethod.GET)
	public Page<ClearingTreeInfo> query(ClearingTreeInfo clearingTreeInfo, Pageable pageable) {
		return clearingTreeService.query(clearingTreeInfo, pageable);
	}
	
}
