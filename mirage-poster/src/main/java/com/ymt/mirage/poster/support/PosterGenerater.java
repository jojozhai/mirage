/**
 * 
 */
package com.ymt.mirage.poster.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ymt.mirage.poster.service.UserPosterService;
import com.ymt.mirage.user.domain.User;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public class PosterGenerater implements Runnable {
	
	private User user;
	
	private Long posterId;
	
	private UserPosterService userPosterService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public PosterGenerater(UserPosterService userPosterService, User user, Long posterId){
		this.user = user;
		this.posterId = posterId;
		this.userPosterService = userPosterService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			userPosterService.create(user, posterId);
		} catch (Exception e) {
			logger.info("生成海报失败.", e);
		}
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the posterId
	 */
	public Long getPosterId() {
		return posterId;
	}

	/**
	 * @param posterId the posterId to set
	 */
	public void setPosterId(Long posterId) {
		this.posterId = posterId;
	}

	/**
	 * @return the userPosterService
	 */
	public UserPosterService getUserPosterService() {
		return userPosterService;
	}

	/**
	 * @param userPosterService the userPosterService to set
	 */
	public void setUserPosterService(UserPosterService userPosterService) {
		this.userPosterService = userPosterService;
	}

}
