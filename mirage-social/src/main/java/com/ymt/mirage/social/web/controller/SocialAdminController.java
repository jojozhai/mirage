/**
 * 
 */
package com.ymt.mirage.social.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.social.dto.CommentInfo;
import com.ymt.mirage.social.service.SocialService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class SocialAdminController {
	
	@Autowired
	private SocialService socialService;

	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
	public void deleteComment(@RequestBody CommentInfo info) throws Exception {
		socialService.deleteComment(info);
	}
}
