/**
 * 
 */
package com.ymt.mirage.vote.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;
import com.ymt.mirage.vote.dto.EnrollInfo;
import com.ymt.mirage.vote.service.EnrollService;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author enroll
 *
 */
@RestController
public class EnrollController {

	@Autowired
	private EnrollService enrollService;

	@RequestMapping(value = "/enroll", method = RequestMethod.POST)
	public EnrollInfo create(@RequestBody EnrollInfo enrollInfo) {
		enrollInfo.setUserId(CurrentUserHolder.getCurrentUserId());
		return enrollService.create(enrollInfo);
	}

	@RequestMapping(value = "/enroll", method = RequestMethod.GET)
	public Page<EnrollInfo> query(EnrollInfo enrollInfo, Pageable pageable) {
		return enrollService.query(enrollInfo, pageable);
	}
	
	@RequestMapping(value = "/enroll/search", method = RequestMethod.GET)
	public EnrollInfo search(@RequestParam String key, @RequestParam Long activityId) {
		return enrollService.search(activityId, key);
	}

	@RequestMapping(value = "/enroll/{id}", method = RequestMethod.GET)
	public EnrollInfo getEnrollInfo(@PathVariable Long id) {
		return enrollService.getInfo(id);
	}
	
	@RequestMapping(value = "/enroll/{id}/vote", method = RequestMethod.POST)
	public SuccessResponse vote(@PathVariable Long id) {
		return new SuccessResponse(enrollService.vote(id, CurrentUserHolder.getCurrentUserId()));
	}

	@RequestMapping(value = "/enroll/{id}", method = RequestMethod.PUT)
	public EnrollInfo update(@RequestBody EnrollInfo enrollInfo) {
		return enrollService.update(enrollInfo);
	}

	@RequestMapping(value = "/enroll/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		enrollService.delete(id);
	}

}
