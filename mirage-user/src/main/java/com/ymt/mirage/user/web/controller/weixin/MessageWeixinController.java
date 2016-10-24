/**
 * 
 */
package com.ymt.mirage.user.web.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.user.dto.MessageInfo;
import com.ymt.mirage.user.service.MessageService;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@RestController
@Profile({"weixin", "app"})
public class MessageWeixinController {
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 查询用户信息
	 * @param id 用户id
	 * @return
	 */
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public Page<MessageInfo> query(MessageInfo info, Pageable pageable) {
		return messageService.query(CurrentUserHolder.getCurrentUserId(), pageable);
	}
	
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public MessageInfo getInfo(@PathVariable Long id) {
        return messageService.getInfo(id);
    }
}