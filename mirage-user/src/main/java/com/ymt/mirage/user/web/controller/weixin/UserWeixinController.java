/**
 * 
 */
package com.ymt.mirage.user.web.controller.weixin;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ymt.mirage.user.dto.ResetPasswordInfo;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.mirage.user.service.UserService;
import com.ymt.pz365.framework.core.context.Property;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@RestController
@Profile({"!admin"})
public class UserWeixinController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
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
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public SuccessResponse create(@RequestBody UserInfo info) throws UnsupportedEncodingException {
        return userService.create(info);
    }
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public SuccessResponse login(@RequestBody UserInfo info) throws UnsupportedEncodingException {
        return userService.login(info);
    }
	
	@RequestMapping(value = "/user/mobile", method = RequestMethod.PUT)
	public void update(@RequestBody MobileUpdateInfo info) throws Exception {
		info.setUserId(CurrentUserHolder.getCurrentUserId());
		userService.update(info);
	}
	
	@RequestMapping(value = "/user/password/reset", method = RequestMethod.PUT)
    public void resetPassword(@RequestBody ResetPasswordInfo resetPasswordInfo) throws Exception {
        userService.resetPassword(resetPasswordInfo);
    }
	
	@RequestMapping(value = "/user/password", method = RequestMethod.PUT)
    public void updatePassword(String oldPassword, String newPassword) throws Exception {
	    logger.info("new password:"+newPassword);
	    logger.info("old password:"+oldPassword);
        userService.updatePassword(CurrentUserHolder.getCurrentUserId(), oldPassword, newPassword);
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