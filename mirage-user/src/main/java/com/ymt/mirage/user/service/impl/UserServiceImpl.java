/**
 * 
 */
package com.ymt.mirage.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.dto.MobileUpdateInfo;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.mirage.user.repository.impl.UserSpec;
import com.ymt.mirage.user.service.UserService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.core.context.Property;
import com.ymt.pz365.framework.core.exception.PzException;
import com.ymt.pz365.framework.weixin.service.WeixinUserService;
import com.ymt.pz365.framework.weixin.support.WeixinAccessToken;
import com.ymt.pz365.framework.weixin.support.WeixinIdAware;
import com.ymt.pz365.framework.weixin.support.WeixinUserInfo;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements WeixinUserService, UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails getUser(WeixinAccessToken accessToken) {
		User user;
		if(accessToken == null) {
			user = userRepository.findByUsername("1");
		}else{
			if(StringUtils.isNotBlank(accessToken.getUnionid())){
				user = userRepository.findByWeixinUnionId(accessToken.getUnionid());  
				if(user == null) {
				    user = userRepository.findByWeixinOpenId(accessToken.getOpenid());
				    if(user != null){
				        user.setWeixinUnionId(accessToken.getUnionid());
				    }
				}
			}else{
				user = userRepository.findByWeixinOpenId(accessToken.getOpenid());
			}
		}
		return user;
	}

	@Override
	public UserDetails regist(WeixinIdAware info) {
		return registUser(info);
	}
	
	private User registUser(WeixinIdAware weixinIdAware) {
		User user;
		if(StringUtils.isNotBlank(weixinIdAware.getUnionid())){
			user = userRepository.findByWeixinUnionId(weixinIdAware.getUnionid());  
		}else{
			user = userRepository.findByWeixinOpenId(weixinIdAware.getOpenid());
		}
		if(user == null) {
			user = new User();
			org.springframework.beans.BeanUtils.copyProperties(weixinIdAware, user);
			user.setWeixinOpenId(weixinIdAware.getOpenid());
			user.setWeixinUnionId(weixinIdAware.getUnionid());
			user.setPassword(passwordEncoder.encode("123456"));
			user.setUsername(RandomStringUtils.randomNumeric(11));
			if(StringUtils.isBlank(user.getSex())){
				user.setSex("0");
			}
			user.setLevel("1");
			user.setTags("");
			userRepository.save(user);
		}
		return user;
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public Page<UserInfo> query(UserInfo info, Pageable pageable) {
		Page<User> users = userRepository.findAll(new UserSpec(info), pageable);
		return QueryResultConverter.convert(users, pageable, new AbstractDomain2InfoConverter<User, UserInfo>() {
			@Override
			protected void doConvert(User domain, UserInfo info) throws Exception {
				info.setVipValid(domain.isVipValidation());
			}
		});
	}

	@Override
	public void update(Long id, Property property) throws Exception {
		User user = userRepository.findOne(id);
		if(StringUtils.equals("point", property.getName())) {
		    if(NumberUtils.isNumber(property.getValue())){
		        user.setPoint(user.getPoint() + new Integer(property.getValue()));
		    }
		}else{
		    BeanUtils.setProperty(user, property.getName(), property.getValue());
		}
	}

	@Override
	public UserInfo getInfo(Long userId) {
		User user =userRepository.findOne(userId);
		UserInfo info = new UserInfo();
		org.springframework.beans.BeanUtils.copyProperties(user, info);
		info.setVipValid(user.isVipValidation());
		return info;
	}

	@Override
	public void update(Long id, List<Property> propertys) throws Exception {
		User user = userRepository.findOne(id);
		for (Property property : propertys) {
			if(StringUtils.equals("birthday", property.getName())){
				String value = StringUtils.replace(StringUtils.remove(StringUtils.remove(property.getValue(), "Z"), ".000"), "T", " ");
				Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
				user.setBirthday(date);
				userRepository.save(user);
			}else{
				BeanUtils.setProperty(user, property.getName(), property.getValue());
			}
		}
	}

	@Override
	public void update(MobileUpdateInfo info) {
		User user = userRepository.findOne(info.getUserId());
		if(!StringUtils.equals(user.getMobile(), info.getOldMobile())){
			throw new PzException("旧号码错误");
		}
		user.setMobile(info.getNewMobile());
	}

    @Override
    public void updateUserWeixinInfo(UserDetails userD, WeixinUserInfo weixinUserInfo) {
        if(userD != null && weixinUserInfo != null) {
            User user = (User)userD;
            if(StringUtils.isBlank(user.getNickname())){
                user.setNickname(weixinUserInfo.getNickname());
            }
            if(StringUtils.isBlank(user.getHeadimgurl())){
                user.setHeadimgurl(weixinUserInfo.getHeadimgurl());
            }
            userRepository.save(user); 
        }
    }

}
