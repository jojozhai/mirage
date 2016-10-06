/**
 * 
 */
package com.ymt.mirage.user.web.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.mirage.user.service.UserService;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@RestController
@Profile("admin")
public class UserAdminController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 查询用户信息
	 * @param id 用户id
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Page<UserInfo> query(UserInfo info, Pageable pageable, @AuthenticationPrincipal User user) {
		return userService.query(info, pageable);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public UserInfo getInfo(@PathVariable Long id) {
        return userService.getInfo(id);
    }
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public UserInfo update(@RequestBody UserInfo userInfo) throws Exception {
        return userService.update(userInfo);
    }
	
}