/**
 * 
 */
package com.ymt.mirage.social.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.social.dto.PostInfo;
import com.ymt.mirage.social.service.PostService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public PostInfo create(@RequestBody PostInfo postInfo) {
		return postService.create(postInfo, CurrentUserHolder.getCurrentUserId());
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public Page<PostInfo> query(PostInfo postInfo, Pageable pageable) {
		return postService.query(postInfo, pageable);
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		postService.delete(id);
	}
}
