/**
 * 
 */
package com.ymt.mirage.social.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymt.mirage.social.domain.Comment;
import com.ymt.mirage.social.dto.CommentInfo;
import com.ymt.mirage.social.dto.Commentable;
import com.ymt.mirage.social.dto.DeleteAll;
import com.ymt.mirage.social.repository.CommentRepository;
import com.ymt.mirage.social.repository.spec.CommentSpec;
import com.ymt.mirage.social.service.CommentService;
import com.ymt.mirage.social.service.SocialService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月23日
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private SocialService socialService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Page<CommentInfo> query(CommentInfo commentInfo, Pageable pageable) {
		Page<Comment> pageData = commentRepository.findAll(new CommentSpec(commentInfo), pageable);
		return QueryResultConverter.convert(pageData, CommentInfo.class, pageable);
	}
	
    @Override
    public Page<CommentInfo> queryWithReply(CommentInfo commentInfo, Pageable pageable) {
        Page<Comment> pageData = commentRepository.findByTargetAndTargetIdAndReplyToIdIsNull(commentInfo.getTarget(), commentInfo.getTargetId(), pageable);
        return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Comment, CommentInfo>() {
            @Override
            protected void doConvert(Comment domain, CommentInfo info) throws Exception {
                List<Comment> replys = commentRepository.findByCommentId(domain.getId(), new Sort(Direction.ASC, "createdTime"));
                info.setReplys(QueryResultConverter.convert(replys, CommentInfo.class));
            }
        });
    }

	@Override
	public CommentInfo create(CommentInfo commentInfo) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentInfo, comment);
		commentInfo.setId(commentRepository.save(comment).getId());
		return commentInfo;
	}

	@Override
	public CommentInfo getInfo(Long id) {
		Comment comment = commentRepository.findOne(id);
		CommentInfo info = new CommentInfo();
		BeanUtils.copyProperties(comment, info);
		return info;
	}

	@Override
	public CommentInfo update(CommentInfo commentInfo) {
		Comment comment = commentRepository.findOne(commentInfo.getId());
		BeanUtils.copyProperties(commentInfo, comment);
		commentRepository.save(comment);
		return commentInfo;
	}

	@Override
	public void delete(Long id) throws Exception {
//		commentRepository.delete(id);
		socialService.deleteComment(getInfo(id));
	}

	@Override
	public void deleteAll(DeleteAll ids) throws Exception {
		for (Long id : ids.getIds()) {
			delete(id);
		}
	}

	@Override
	public Comment[] getComments(Commentable commentable) throws Exception {
		String commentContent = commentable.getComment();
		if(StringUtils.isBlank(commentContent)) {
			commentContent = "[]";
		}
		return mapper.readValue(commentContent, Comment[].class);
	}

    @Override
    public Comment[] getComments(Long id, String target) {
        List<Comment> comments = commentRepository.findByTargetAndTargetId(target, id);
        return comments.toArray(new Comment[comments.size()]);
    }

}
