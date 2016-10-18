/**
 * 
 */
package com.ymt.mirage.social.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import com.ymt.mirage.social.repository.PraiseRepository;
import com.ymt.mirage.social.repository.spec.CommentSpec;
import com.ymt.mirage.social.service.CommentService;
import com.ymt.mirage.social.service.SocialService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
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
	private PraiseRepository praiseRepository;
	
	@Autowired
	private SocialService socialService;
	
	@Autowired
	private UserRepository userRepository;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Page<CommentInfo> query(final CommentInfo commentInfo, Pageable pageable) {
	    if(commentInfo.isWithReply()){
	        return queryWithReply(commentInfo, pageable);
	    }else{
	        Page<Comment> pageData = commentRepository.findAll(new CommentSpec(commentInfo), pageable);
	        final boolean hasUser = commentInfo.getUserId() != null;
	        final Long userId = commentInfo.getUserId();
	        return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Comment, CommentInfo>() {
                @Override
                protected void doConvert(Comment domain, CommentInfo info) throws Exception {
                    if(hasUser) {
                        info.setPraised(socialService.getPraise("comment", domain.getId(), userId));
                    }
                }
            });
	    }
	}
	
    @Override
    public Page<CommentInfo> queryWithReply(CommentInfo commentInfo, Pageable pageable) {
        Page<Comment> pageData = commentRepository.findByTargetAndTargetIdAndReplyToIdIsNullAndDisable(commentInfo.getTarget(), commentInfo.getTargetId(), false, pageable);
        final boolean hasUser = commentInfo.getUserId() != null;
        final Long userId = commentInfo.getUserId();
        return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Comment, CommentInfo>() {
            @Override
            protected void doConvert(Comment domain, CommentInfo info) throws Exception {
                List<Comment> replys = commentRepository.findByCommentIdAndDisable(domain.getId(), false, new Sort(Direction.DESC, "createdTime"));
                info.setReplys(QueryResultConverter.convert(replys, CommentInfo.class));
                if(hasUser) {
                    info.setPraised(socialService.getPraise("comment", domain.getId(), userId));
                }
            }
        });
    }
    
    @Override
    public List<Boolean> getCommentPraise(CommentInfo commentInfo, Pageable pageable, Long currentUserId) {
        Page<Comment> pageData = commentRepository.findByTargetAndTargetIdAndReplyToIdIsNullAndDisable(commentInfo.getTarget(), commentInfo.getTargetId(), false, pageable);
        List<Boolean> result = new ArrayList<Boolean>();
        List<Comment> comments = pageData.getContent();
        for (Comment comment : comments) {
            result.add(praiseRepository.findByCreaterIdAndTargetId(currentUserId, comment.getId()) != null);
        }
        return result;
    }


	@Override
	public CommentInfo create(CommentInfo commentInfo) throws Exception {
	    User user = userRepository.findByUsername("1");
	    
	    socialService.comment(commentInfo, user.getId());
	    
//		Comment comment = new Comment();
//		BeanUtils.copyProperties(commentInfo, comment);
//		commentInfo.setId(commentRepository.save(comment).getId());
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
		comment.setContent(commentInfo.getContent());
		commentRepository.save(comment);
		return commentInfo;
	}

	@Override
	public void delete(Long id) throws Exception {
	    Comment comment = commentRepository.findOne(id);
	    comment.setDisable(true);
	    commentRepository.save(comment);
//		commentRepository.delete(id);
//		socialService.deleteComment(getInfo(id));
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
        List<Comment> comments = commentRepository.findByTargetAndTargetIdAndDisable(target, id, false);
        return comments.toArray(new Comment[comments.size()]);
    }

    @Override
    public CommentInfo getReply(Long id) {
        List<Comment> replys = commentRepository.findByCommentIdAndDisable(id, false, new Sort(Direction.DESC, "createdTime"));
        if(CollectionUtils.isNotEmpty(replys)){
            return getInfo(replys.get(0).getId());
        }
        return null;
    }

    
}
