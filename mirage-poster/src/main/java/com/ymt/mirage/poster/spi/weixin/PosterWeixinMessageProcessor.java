/**
 * 
 */
package com.ymt.mirage.poster.spi.weixin;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.ymt.mirage.poster.domain.Poster;
import com.ymt.mirage.poster.domain.UserPoster;
import com.ymt.mirage.poster.repository.PosterRepository;
import com.ymt.mirage.poster.repository.UserPosterRepository;
import com.ymt.mirage.poster.service.UserPosterService;
import com.ymt.mirage.poster.support.PosterGenerater;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.framework.weixin.service.WeixinService;
import com.ymt.pz365.framework.weixin.service.WeixinUserService;
import com.ymt.pz365.framework.weixin.spi.message.WeixinMessageProcessor;
import com.ymt.pz365.framework.weixin.support.message.ReceivedMessage;

/**
 * @author zhailiang
 * @since 2016年5月6日
 */
@Component
public class PosterWeixinMessageProcessor implements WeixinMessageProcessor {
	
	@Autowired
	private PosterRepository posterRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserPosterRepository userPosterRepository;
	
	@Autowired
	private UserPosterService userPosterService;
	
	@Autowired 
	@Qualifier("posterTaskExecutor")
	private TaskExecutor posterTaskExecutor;
	
	@Autowired
	private WeixinService weixinService;
	
	@Autowired
	private WeixinUserService userService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.weixin.spi.message.WeixinMessageProcessor#support(com.ymt.pz365.framework.weixin.dto.ReceivedMessage)
	 */
	@Override
	public boolean support(ReceivedMessage message) {
		if(StringUtils.equals("text", message.getMsgType())){
			Poster poster = posterRepository.findByKey(message.getContent());
			return poster != null;
		}else{
			return message.isNewUserScanQrcodeEvent() || message.isOldUserScanQrcodeEvent();
		}
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.weixin.spi.message.WeixinMessageProcessor#process(com.ymt.pz365.framework.weixin.dto.ReceivedMessage)
	 */
	@Override
	public void process(ReceivedMessage message) throws Exception {
		logger.info("process message "+ReflectionToStringBuilder.toString(message));
		
		User user = getSender(message);
		Poster poster = getPoster(message);
		
		if(poster != null && user != null) {
			posterTaskExecutor.execute(new PosterGenerater(userPosterService, user.getId(), poster.getId()));
		}
		
		UserPoster senderUserPoster = getSenderUserPoster(message);
		if(senderUserPoster != null) {
			if(message.isNewUserScanQrcodeEvent() || 
					(message.isOldUserScanQrcodeEvent() && !senderUserPoster.getPoster().isOnlyNewUserAddPoint())){
				userPosterService.addSenderPoint(senderUserPoster.getId(), user.getId());
			}
		}
	}

	private User getSender(ReceivedMessage message) {
		User user = userRepository.findByWeixinOpenId(message.getFromUserName());
		if(user == null) {
			user = (User) userService.regist(weixinService.getUserInfo(message.getFromUserName()));
		}
		return user;
	}

	private Poster getPoster(ReceivedMessage message) {
		if(StringUtils.equals("text", message.getMsgType())){
			return posterRepository.findByKey(message.getContent());
		}else{
			UserPoster userPoster = getSenderUserPoster(message);
			if(userPoster != null){
				return userPoster.getPoster();
			}
			return null;
		}
	}

	private UserPoster getSenderUserPoster(ReceivedMessage message) {
		String userPosterId = null;
		if(message.isNewUserScanQrcodeEvent()){
			userPosterId = StringUtils.substringAfter(message.getEventKey(), "qrscene_");
		}else if(message.isOldUserScanQrcodeEvent()){
			userPosterId = message.getEventKey();
		}
		if(StringUtils.isNotBlank(userPosterId)) {
			return userPosterRepository.findOne(new Long(userPosterId));
		}
		return null;
	}

}
