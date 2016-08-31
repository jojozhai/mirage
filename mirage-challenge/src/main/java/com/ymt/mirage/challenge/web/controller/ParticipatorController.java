/**
 * 
 */
package com.ymt.mirage.challenge.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.challenge.dto.ParticipatorInfo;
import com.ymt.mirage.challenge.service.ParticipatorService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class ParticipatorController {
	
	@Autowired
	private ParticipatorService participatorService;
	
	/**
	 * 
	 * @param id 用户挑战的id
	 * @return
	 * @author zhailiang
	 * @since 2016年5月11日
	 */
	@RequestMapping(value = "/participator/{id}", method = RequestMethod.GET)
	public ParticipatorInfo getInfo(@PathVariable Long id) {
		return participatorService.getInfo(id, CurrentUserHolder.getCurrentUserId());
	}

}
