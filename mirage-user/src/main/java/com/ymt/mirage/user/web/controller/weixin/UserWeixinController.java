/**
 * 
 */
package com.ymt.mirage.user.web.controller.weixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.dto.MobileUpdateInfo;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.mirage.user.service.UserService;
import com.ymt.pz365.framework.core.context.Property;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@RestController
@Profile({"weixin", "app"})
public class UserWeixinController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/current", method = RequestMethod.GET)
	public UserInfo getCurrentUser(){
		return userService.getInfo(CurrentUserHolder.getCurrentUserId());
	}
	
	/**
	 * 查询用户信息
	 * @param id 用户id
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Page<UserInfo> query(UserInfo info, Pageable pageable, @AuthenticationPrincipal User user) {
		return userService.query(info, pageable);
	}
	
	@RequestMapping(value = "/user/mobile", method = RequestMethod.PUT)
	public void update(@RequestBody MobileUpdateInfo info) throws Exception {
		info.setUserId(CurrentUserHolder.getCurrentUserId());
		userService.update(info);
	}
	
	@RequestMapping(value = "/user/property", method = RequestMethod.PUT)
	public void update(@RequestBody Property property) throws Exception {
		userService.update(CurrentUserHolder.getCurrentUserId(), property);
	}
	
	@RequestMapping(value = "/user/propertys", method = RequestMethod.PUT)
	public void update(@RequestBody List<Property> propertys) throws Exception {
		userService.update(CurrentUserHolder.getCurrentUserId(), propertys);
	}
	
}