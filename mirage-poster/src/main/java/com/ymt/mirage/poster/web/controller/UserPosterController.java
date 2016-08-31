/**
 * 
 */
package com.ymt.mirage.poster.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.poster.dto.UserPosterInfo;
import com.ymt.mirage.poster.service.UserPosterService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class UserPosterController {
	
	@Autowired
	private UserPosterService userPosterService;
	
	@RequestMapping(value = "/userPoster", method = RequestMethod.GET)
	public Page<UserPosterInfo> query(UserPosterInfo condition, Pageable pageable) {
		return userPosterService.query(condition, pageable);
	}
	
	@RequestMapping(value = "/userPoster/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		userPosterService.delete(id);
	}

}
