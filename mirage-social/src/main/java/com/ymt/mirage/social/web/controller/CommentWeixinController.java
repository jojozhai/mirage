/**
 * 
 */
package com.ymt.mirage.social.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.social.dto.CommentInfo;
import com.ymt.mirage.social.service.CommentService;
import com.ymt.mirage.social.service.SocialService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile({"weixin","app"})
public class CommentWeixinController {
	
	@Autowired
	private SocialService socialService;
	
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public CommentInfo comment(@RequestBody CommentInfo commentInfo) throws Exception {
		Long currentUserId = CurrentUserHolder.getCurrentUserId();
		if(currentUserId == null) {
			throw new PzException("不能识别当前用户");
		}
		return socialService.comment(commentInfo, currentUserId);
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public Page<CommentInfo> query(CommentInfo commentInfo, Pageable pageable) throws Exception {
		return commentService.query(commentInfo, pageable);
	}
	
	/**
	 * @param commentInfo
	 * @param pageable
	 * @return
	 * @throws Exception
	 * @author zhailiang
	 * @since 2016年8月9日
	 */
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
    public Page<CommentInfo> queryWithReply(CommentInfo commentInfo, Pageable pageable) throws Exception {
        return commentService.queryWithReply(commentInfo, pageable);
    }
	
}
