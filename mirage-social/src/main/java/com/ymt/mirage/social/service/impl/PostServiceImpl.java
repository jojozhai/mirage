/**
 * 
 */
package com.ymt.mirage.social.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymt.mirage.social.domain.Comment;
import com.ymt.mirage.social.domain.Post;
import com.ymt.mirage.social.domain.Praise;
import com.ymt.mirage.social.dto.PostInfo;
import com.ymt.mirage.social.repository.PostRepository;
import com.ymt.mirage.social.repository.spec.PostSpec;
import com.ymt.mirage.social.service.PostService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public PostInfo create(PostInfo postInfo) {
		Post post = new Post();
		BeanUtils.copyProperties(postInfo, post);
		postRepository.save(post);
		postInfo.setId(post.getId());
		return postInfo;
	}

	@Override
	public PostInfo create(PostInfo postInfo, Long currentUserId) {
		User user = userRepository.findOne(currentUserId);
		postInfo.setCreaterId(user.getId());
		postInfo.setCreaterName(user.getNickname());
		postInfo.setCreaterHeadimgurl(user.getHeadimgurl());
		return create(postInfo);
	}

	@Override
	public Page<PostInfo> query(PostInfo postInfo, Pageable pageable) {
		Page<Post> pageData = postRepository.findAll(new PostSpec(postInfo), pageable);
		return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Post, PostInfo>() {
			@Override
			protected void doConvert(Post domain, PostInfo info) throws Exception {
				info.setComments(objectMapper.readValue(domain.getComment(), Comment[].class));
				info.setPraises(objectMapper.readValue(domain.getPraise(), Praise[].class));
			}
		});
	}

	@Override
	public void delete(Long id) {
		postRepository.delete(id);
	}

}
