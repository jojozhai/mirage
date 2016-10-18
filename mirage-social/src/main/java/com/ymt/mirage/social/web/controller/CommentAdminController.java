/**
 * 
 */
package com.ymt.mirage.social.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.social.dto.CommentInfo;
import com.ymt.mirage.social.dto.DeleteAll;
import com.ymt.mirage.social.service.CommentService;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@RestController
@Profile("admin")
public class CommentAdminController {
	
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public CommentInfo create(@RequestBody CommentInfo commentInfo) throws Exception {
		return commentService.create(commentInfo);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public Page<CommentInfo> query(CommentInfo commentInfo, Pageable pageable) {
		return commentService.query(commentInfo, pageable);
	}

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
	public CommentInfo getInfo(@PathVariable Long id) throws Exception {
		return commentService.getInfo(id);
	}
	
	@RequestMapping(value = "/comment/{id}/reply", method = RequestMethod.GET)
    public CommentInfo getReply(@PathVariable Long id) throws Exception {
        return commentService.getReply(id);
    }

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
	public CommentInfo update(@RequestBody CommentInfo commentInfo) {
		return commentService.update(commentInfo);
	}

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) throws Exception {
		commentService.delete(id);
	}
	
	@RequestMapping(value = "/comment/batch", method = RequestMethod.POST)
	public void delete(@RequestBody DeleteAll ids) throws Exception {
		commentService.deleteAll(ids);
	}

}
