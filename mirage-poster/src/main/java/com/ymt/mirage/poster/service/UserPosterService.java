/**
 * 
 */
package com.ymt.mirage.poster.service;

import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestClientException;

import com.ymt.mirage.poster.dto.UserPosterInfo;
import com.ymt.mirage.user.domain.User;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface UserPosterService {

	void create(User user, Long posterId) throws Exception;

	Page<UserPosterInfo> query(UserPosterInfo condition, Pageable pageable);

	void delete(Long id);

	void addSenderPoint(Long senderUserPosterId , Long scanerId) throws RestClientException, URISyntaxException, Exception;

}
