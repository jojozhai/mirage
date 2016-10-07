/**
 * 
 */
package com.ymt.mirage.umeditor.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.umeditor.dto.EditInfo;
import com.ymt.mirage.umeditor.service.UmeditorService;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class UmeditorAdminController {
	
	@Autowired
	private UmeditorService umeditorService;

	@RequestMapping(value = "/domain/property", method = RequestMethod.PUT)
	public void setProperty(@RequestBody EditInfo editInfo) throws Exception {
		umeditorService.setProperty(editInfo);
	}

	@RequestMapping(value = "/domain/property", method = RequestMethod.GET)
	public SuccessResponse getProperty(EditInfo editInfo) throws Exception {
		return new SuccessResponse(umeditorService.getProperty(editInfo));
	}
}
