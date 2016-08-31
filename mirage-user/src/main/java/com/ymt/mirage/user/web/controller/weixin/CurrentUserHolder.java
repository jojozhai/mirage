/**
 * 
 */
package com.ymt.mirage.user.web.controller.weixin;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ymt.mirage.user.domain.User;


/**
 * @author zhailiang
 *
 */
public class CurrentUserHolder {
	
	public static User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null){
			return null;
		}else{
			Object principal = authentication.getPrincipal();
			if(principal != null){
				if(principal instanceof User){
					return (User)principal;
				}else{
					return null;
				}
			}else{
				return null;
			}
		}
	}
	
	public static Long getCurrentUserId() {
		User user = getCurrentUser();
		if(user == null) {
			return null;
		}else{
			return user.getId();
		}
	}

}
