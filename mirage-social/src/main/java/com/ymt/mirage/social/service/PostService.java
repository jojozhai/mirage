/**
 * 
 */
package com.ymt.mirage.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.social.dto.PostInfo;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public interface PostService {

	PostInfo create(PostInfo postInfo);
	
	PostInfo create(PostInfo postInfo, Long currentUserId);

	Page<PostInfo> query(PostInfo postInfo, Pageable pageable);

	void delete(Long id);

}
