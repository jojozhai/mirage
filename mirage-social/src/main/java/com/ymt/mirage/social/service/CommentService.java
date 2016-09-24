/**
 * 
 */
package com.ymt.mirage.social.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.social.domain.Comment;
import com.ymt.mirage.social.dto.CommentInfo;
import com.ymt.mirage.social.dto.Commentable;
import com.ymt.mirage.social.dto.DeleteAll;

/**
 * @author zhailiang
 * @since 2016年5月23日
 */
public interface CommentService {
	
	Page<CommentInfo> query(CommentInfo commentInfo, Pageable pageable);
	
	CommentInfo create(CommentInfo commentInfo);

	CommentInfo getInfo(Long id);

	CommentInfo update(CommentInfo commentInfo);

	void delete(Long id) throws Exception;

	void deleteAll(DeleteAll ids) throws Exception;

	Comment[] getComments(Commentable commentable) throws Exception;

    Page<CommentInfo> queryWithReply(CommentInfo commentInfo, Pageable pageable);

    Comment[] getComments(Long id, String target);

    List<Boolean> getCommentPraise(CommentInfo commentInfo, Pageable pageable, Long currentUserId);

}
