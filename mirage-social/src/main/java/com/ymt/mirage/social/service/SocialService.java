/**
 * 
 */
package com.ymt.mirage.social.service;

import java.util.List;

import com.ymt.mirage.social.domain.Comment;
import com.ymt.mirage.social.dto.CommentInfo;
import com.ymt.mirage.social.dto.PraiseInfo;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public interface SocialService {
	
	boolean praise(PraiseInfo praiseInfo, Long currentUserId) throws Exception;
	
	boolean praise(PraiseInfo praiseInfo, Long currentUserId, boolean add) throws Exception;

	CommentInfo comment(CommentInfo commentInfo, Long currentUserId) throws Exception;

	void deleteComment(CommentInfo info) throws Exception;

	List<Comment> queryComment(CommentInfo commentInfo);

}
