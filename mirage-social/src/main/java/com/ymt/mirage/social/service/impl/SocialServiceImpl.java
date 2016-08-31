/**
 * 
 */
package com.ymt.mirage.social.service.impl;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymt.mirage.social.domain.Comment;
import com.ymt.mirage.social.domain.Praise;
import com.ymt.mirage.social.dto.CommentInfo;
import com.ymt.mirage.social.dto.Commentable;
import com.ymt.mirage.social.dto.PraiseInfo;
import com.ymt.mirage.social.dto.Praiseable;
import com.ymt.mirage.social.repository.CommentRepository;
import com.ymt.mirage.social.repository.PraiseRepository;
import com.ymt.mirage.social.repository.spec.CommentSpec;
import com.ymt.mirage.social.service.SocialService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Service("praiseService")
@Transactional
public class SocialServiceImpl implements SocialService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
    private PraiseRepository praiseRepository;
	
	@Value("${mirage.social.comment.mix:false}")
	private boolean commentMix;
	
	@Value("${mirage.social.praise.mix:false}")
    private boolean praiseMix;

	private Praise addPraise(Praiseable praiseable, Praise praise) throws Exception {
		boolean isNewPraise = true;
		String content = praiseable.getPraise();
		if(StringUtils.isBlank(content)){
			content = "[]";
		}
		Praise[] praises = objectMapper.readValue(content, Praise[].class);
		if(ArrayUtils.isEmpty(praises)){
			praises = new Praise[]{praise};
		}else{
			for (int i = 0; i < praises.length; i++) {
				if(praises[i].getCreaterId().equals(praise.getCreaterId())) {
					isNewPraise = false;
					break;
				}
			}
			if(isNewPraise) {
				praises = (Praise[]) ArrayUtils.add(praises, praise);
			}
		}
		praiseable.setPraise(objectMapper.writeValueAsString(praises));
		if(isNewPraise) {
			return praise;
		}else{
			return null;
		}
	}

	private void addComment(Commentable commentable, Comment comment) throws Exception {
		String content = commentable.getComment();
		if(StringUtils.isBlank(content)){
			content = "[]";
		}
		Comment[] comments = objectMapper.readValue(content, Comment[].class);
		if(ArrayUtils.isEmpty(comments)){
			comments = new Comment[]{comment};
		}else{
			comments = (Comment[]) ArrayUtils.addAll(new Comment[]{comment}, comments);
		}
		commentable.setComment(objectMapper.writeValueAsString(comments));
	}
	
	@Override
	public boolean praise(PraiseInfo praiseInfo, Long currentUserId) throws Exception {
		return praise(praiseInfo, currentUserId, true);
	}

	@Override
	public CommentInfo comment(CommentInfo commentInfo, Long currentUserId) throws Exception {
		User user = userRepository.findOne(currentUserId);
		
		Commentable commentable = getCommentable(commentInfo);
		
		Comment comment = createComment(commentInfo, user);
		
		if(commentable != null && commentMix) {
			addComment(commentable, comment);
		}
		
		CommentInfo info = new CommentInfo();
		BeanUtils.copyProperties(comment, info);
		
		return info;
	}

	private Commentable getCommentable(CommentInfo commentInfo) {
		Commentable commentable = null;
		try {
			String repositoryName = StringUtils.uncapitalize(commentInfo.getTarget())+"Repository";
			PzRepository<?> repository = (PzRepository<?>) applicationContext.getBean(repositoryName);
			commentable = (Commentable) repository.findOne(commentInfo.getTargetId());
		} catch (Exception e) {
			logger.error("获得评论对象失败");
		}
		return commentable;
	}

	private Comment createComment(CommentInfo commentInfo, User user) {
		Comment comment = new Comment();
		comment.setTarget(commentInfo.getTarget());
		comment.setTargetId(commentInfo.getTargetId());
		comment.setCreaterId(user.getId());
		comment.setCreaterName(user.getNickname());
		comment.setCreaterHeadimgurl(user.getHeadimgurl());
		
		if(commentInfo.getReplyToId() != null) {
		    Comment replyTarget = commentRepository.findOne(commentInfo.getReplyToId());
		    if(replyTarget.getComment() == null) {
		        comment.setComment(replyTarget);
		    }else{
		        comment.setComment(replyTarget.getComment());
		    }
		    comment.setReplyToId(commentInfo.getReplyToId());
		    comment.setReplyToUserId(replyTarget.getCreaterId());
	        comment.setReplyToName(replyTarget.getCreaterName());
		}
		
		String content = commentInfo.getContent();
//		if(StringUtils.startsWith(content, "回复 ") && StringUtils.indexOf(content, ":")!=0){
//			content = StringUtils.substringAfter(content, ":");
//		}
//		content = EmojiParser.parseToAliases(content);
		
		comment.setContent(content);
		commentRepository.save(comment);
		return comment;
	}

	@Override
	public void deleteComment(CommentInfo commentInfo) throws Exception {
		String repositoryName = StringUtils.uncapitalize(commentInfo.getTarget())+"Repository";
		PzRepository<?> repository = (PzRepository<?>) applicationContext.getBean(repositoryName);
		Commentable commentable = (Commentable) repository.findOne(commentInfo.getTargetId());
		
		String content = commentable.getComment();
		if(StringUtils.isBlank(content)){
			content = "[]";
		}
		Comment[] comments = objectMapper.readValue(content, Comment[].class);
		int index = 0;
		for (int i = 0; i < comments.length; i++) {
			Comment comment = comments[i];
			if(comment.getId().equals(commentInfo.getId())) {
				index = i;
				break;
			}
		}
		comments = (Comment[]) ArrayUtils.remove(comments, index);
		
		commentable.setComment(objectMapper.writeValueAsString(comments));
		
		commentRepository.delete(commentInfo.getId());
	}

	@Override
	public List<Comment> queryComment(CommentInfo commentInfo) {
		return commentRepository.findAll(new CommentSpec(commentInfo), new Sort(Direction.DESC, "createdTime"));
	}

    @Override
    public boolean praise(PraiseInfo praiseInfo, Long currentUserId, boolean add) throws Exception {
        User user = userRepository.findOne(currentUserId);
        
        String repositoryName = StringUtils.uncapitalize(praiseInfo.getTarget())+"Repository";
        PzRepository<?> repository = (PzRepository<?>) applicationContext.getBean(repositoryName);
        Praiseable praiseable = (Praiseable) repository.findOne(praiseInfo.getTargetId());
        
        Praise praise = praiseRepository.findByCreaterIdAndTargetId(currentUserId, praiseInfo.getTargetId());
        if(praise == null) {
            if(add) {
                praise = new Praise();
                praise.setCreaterId(user.getId());
                praise.setTargetId(praiseInfo.getTargetId());
                praise.setCreaterName(user.getNickname());
                praiseRepository.save(praise);
                
                praiseable.setPraiseCount(praiseable.getPraiseCount() + 1);
                
                return true;
            }
        }else {
            if(!add) {
                praiseRepository.delete(praise);
                praiseable.setPraiseCount(praiseable.getPraiseCount() - 1);
                
                return true;
            }
        }
        
        return false;
    }
	
}
