/**
 * 
 */
package com.ymt.mirage.user.spi.weixin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymt.pz365.framework.weixin.service.WeixinService;
import com.ymt.pz365.framework.weixin.service.WeixinUserService;
import com.ymt.pz365.framework.weixin.spi.message.WeixinMessageProcessor;
import com.ymt.pz365.framework.weixin.support.message.ReceivedMessage;

/**
 * @author zhailiang
 * @since 2016年5月6日
 */
@Component
public class UserWeixinMessageProcessor implements WeixinMessageProcessor {
	
	@Autowired
	private WeixinUserService userService;
	
	@Autowired
	private WeixinService weixinService;
	

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.weixin.spi.message.WeixinMessageProcessor#support(com.ymt.pz365.framework.weixin.dto.ReceivedMessage)
	 */
	@Override
	public boolean support(ReceivedMessage message) {
		return StringUtils.equals(message.getMsgType(), "event") && 
				StringUtils.equals(message.getEvent(), "subscribe");
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.weixin.spi.message.WeixinMessageProcessor#process(com.ymt.pz365.framework.weixin.dto.ReceivedMessage)
	 */
	@Override
	public void process(ReceivedMessage message) throws Exception {
		userService.regist(weixinService.getUserInfo(message.getFromUserName()));
	}

}
