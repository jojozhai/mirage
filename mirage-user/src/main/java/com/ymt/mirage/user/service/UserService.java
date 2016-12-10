/**
 * 
 */
package com.ymt.mirage.user.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ymt.mirage.user.dto.MobileUpdateInfo;
import com.ymt.mirage.user.dto.ResetPasswordInfo;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.pz365.framework.core.context.Property;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author zhailiang
 * @since 2016年5月4日
 */
public interface UserService extends UserDetailsService {

	Page<UserInfo> query(UserInfo info, Pageable pageable);

	void update(Long id, Property property) throws Exception;

	UserInfo getInfo(Long userId);

	void update(Long id, List<Property> propertys) throws Exception;

	void update(MobileUpdateInfo info);

    UserInfo update(UserInfo userInfo);

    SuccessResponse create(UserInfo info) throws UnsupportedEncodingException;

    SuccessResponse login(UserInfo info) throws UnsupportedEncodingException;

    void updatePassword(Long currentUserId, String oldPassword, String newPassword);

    void resetPassword(ResetPasswordInfo resetPasswordInfo);

}
