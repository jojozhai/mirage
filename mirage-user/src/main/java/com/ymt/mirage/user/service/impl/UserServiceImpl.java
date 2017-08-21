/**
 * 
 */
package com.ymt.mirage.user.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.user.domain.Message;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.dto.MobileUpdateInfo;
import com.ymt.mirage.user.dto.ResetPasswordInfo;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.mirage.user.repository.MessageRepository;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.mirage.user.repository.impl.UserSpec;
import com.ymt.mirage.user.service.UserService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.core.context.Property;
import com.ymt.pz365.framework.core.exception.PzException;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;
import com.ymt.pz365.framework.param.service.ParamService;
import com.ymt.pz365.framework.weixin.service.WeixinService;
import com.ymt.pz365.framework.weixin.service.WeixinUserService;
import com.ymt.pz365.framework.weixin.support.WeixinAccessToken;
import com.ymt.pz365.framework.weixin.support.WeixinIdAware;
import com.ymt.pz365.framework.weixin.support.WeixinUserInfo;
import com.ymt.pz365.framework.weixin.support.message.TemplateMessage;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements WeixinUserService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
	private ParamService paramService;
    
    @Autowired
	private WeixinService weixinService;
    

	@Value("${poster.user.point.change.template.id:9uCLyMmViz6cYmM_ea0Hw2ofHa4gB5yf2WozMY72OC8}")
	private String pointChangeMessageTemplateId;

    @Override
    public UserDetails getUser(WeixinAccessToken accessToken) {
        User user;
        if (accessToken == null) {
            user = userRepository.findByUsername("1");
        } else {
            if (StringUtils.isNotBlank(accessToken.getUnionid())) {
                user = userRepository.findByWeixinUnionId(accessToken.getUnionid());
                if (user == null) {
                    user = userRepository.findByWeixinOpenId(accessToken.getOpenid());
                    if (user != null) {
                        user.setWeixinUnionId(accessToken.getUnionid());
                    }
                }
            } else {
                user = userRepository.findByWeixinOpenId(accessToken.getOpenid());
            }
        }
        return user;
    }

    @Override
    public UserDetails regist(WeixinIdAware info) {
        return registUser(info);
    }
    
    private Set<String> keys = new HashSet<>();

    private User registUser(WeixinIdAware weixinIdAware) {
    	
    	User user = null;
    	
    	try {
    		if(!keys.contains(weixinIdAware.getOpenid())) {
        		
        		keys.add(weixinIdAware.getOpenid());
    	        
    	        if (StringUtils.isNotBlank(weixinIdAware.getUnionid())) {
    	            user = userRepository.findByWeixinUnionId(weixinIdAware.getUnionid());
    	        } else {
    	            user = userRepository.findByWeixinOpenId(weixinIdAware.getOpenid());
    	        }
    	        if (user == null) {
    	            user = new User();
    	            org.springframework.beans.BeanUtils.copyProperties(weixinIdAware, user);
    	            user.setWeixinOpenId(weixinIdAware.getOpenid());
    	            user.setWeixinUnionId(weixinIdAware.getUnionid());
    	            user.setPassword(passwordEncoder.encode("123456"));
    	            user.setUsername(RandomStringUtils.randomNumeric(11));
    	            if (StringUtils.isBlank(user.getSex())) {
    	                user.setSex("0");
    	            }
    	            user.setLevel("1");
    	            user.setTags("");
    	            userRepository.saveAndFlush(user);
    	        }
    	        
    	        keys.remove(weixinIdAware.getOpenid());
        	}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			keys.remove(weixinIdAware.getOpenid());
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
        if (StringUtils.equals("point", property.getName())) {
            if (NumberUtils.isNumber(property.getValue())) {
                user.setPoint(user.getPoint() + new Integer(property.getValue()));
            }
        } else if (StringUtils.equals("beans", property.getName())) {
            if (NumberUtils.isNumber(property.getValue())) {
                user.setBeans(user.getBeans() + new Integer(property.getValue()));
            }
        } else if (StringUtils.equals("birthday", property.getName())) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(property.getValue());
            user.setBirthday(date);
            userRepository.save(user);
        } else if (StringUtils.equals("password", property.getName())) {
            user.setPassword(passwordEncoder.encode(property.getValue()));
            userRepository.save(user);
        } else if (StringUtils.equals("mobile", property.getName())) {
            User exist = userRepository.findByUsername(property.getValue());
            if (exist != null && !exist.getId().equals(user.getId())) {
                throw new PzException("电话号码已被占用");
            } else {
                user.setMobile(property.getValue());
//                user.setUsername(property.getValue());
            }
        } else {
            BeanUtils.setProperty(user, property.getName(), property.getValue());
        }
    }

    @Override
    public UserInfo getInfo(Long userId) {
        User user = userRepository.findOne(userId);
        UserInfo info = new UserInfo();
        org.springframework.beans.BeanUtils.copyProperties(user, info);
        info.setVipValid(user.isVipValidation());
        List<Message> unreadMessages = messageRepository.findByUserIdAndRead(userId, false);
        info.setUnreadMessages(unreadMessages.size());
        return info;
    }

    @Override
    public void update(Long id, List<Property> propertys) throws Exception {
        for (Property property : propertys) {
            update(id, property);
        }
    }

    @Override
    public void update(MobileUpdateInfo info) {
        User user = userRepository.findOne(info.getUserId());
        if (!StringUtils.equals(user.getMobile(), info.getOldMobile())) {
            throw new PzException("旧号码错误");
        }
        user.setMobile(info.getNewMobile());
    }

    @Override
    public void updateUserWeixinInfo(UserDetails userD, WeixinUserInfo weixinUserInfo) {
        if (userD != null && weixinUserInfo != null) {
            User user = (User) userD;
            if (StringUtils.isBlank(user.getNickname())) {
                user.setNickname(weixinUserInfo.getNickname());
            }
            if (StringUtils.isBlank(user.getHeadimgurl())) {
                user.setHeadimgurl(weixinUserInfo.getHeadimgurl());
            }
            userRepository.save(user);
        }
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        User user = userRepository.findOne(userInfo.getId());
        user.setMoneyPlus(userInfo.getMoneyPlus());

        user.setVip(userInfo.isVip());
        user.setCity(userInfo.getCity());

        userRepository.save(user);
        return userInfo;
    }

    @Override
    public SuccessResponse create(UserInfo info) throws UnsupportedEncodingException {
        User user = userRepository.findByUsername(info.getUsername());
        if (user == null) {
            user = new User();
            user.setUsername(info.getUsername());
            user.setNickname(info.getNickname());
            user.setPassword(passwordEncoder.encode(info.getPassword()));
            user.setSex(info.getSex());
            user.setCity(info.getCity());
            user.setMobile(info.getUsername());

            user.setAddress("");
            user.setAge(18);
            user.setBeans(0);
            user.setBirthday(new DateTime(1990, 1, 1, 0, 0, 0).toDate());
            user.setCar("");
            user.setCity("");
            user.setCountry("");
            user.setHeadimgurl("");
            user.setJob("");
            user.setLevel("1");
            user.setMobile("");
            user.setPoint(0);
            user.setProvince("");
            user.setRealname("");

            user.setTags("");
            user.setVipExpired(new DateTime(1990, 1, 1, 0, 0, 0).toDate());
            user.setWeixin("");
            user.setWeixinOpenId("");
            user.setWeixinUnionId("");

            userRepository.save(user);

            String userAndPass = user.getUsername() + ":" + info.getPassword();
            return new SuccessResponse("Basic " + Base64.encodeBase64String(userAndPass.getBytes("UTF-8")));
        }
        throw new PzException("手机号已存在");
    }

    @Autowired
    private CacheManager cacheManager;

    @Override
    public SuccessResponse login(UserInfo info) throws UnsupportedEncodingException {
        User user = userRepository.findByUsername(info.getUsername());
        if (user != null) {
            if (passwordEncoder.matches(info.getPassword(), user.getPassword())) {
                String userAndPass = user.getUsername() + ":" + info.getPassword();
                return new SuccessResponse("Basic " + Base64.encodeBase64String(userAndPass.getBytes("UTF-8")));
            }
        }
        throw new PzException("用户名或密码错误");
    }

    @Override
    public void updatePassword(Long currentUserId, String oldPassword, String newPassword) {
        User user = userRepository.findOne(currentUserId);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new PzException("旧密码错误");
        }
    }

    @Override
    public void resetPassword(ResetPasswordInfo resetPasswordInfo) {
        User user = userRepository.findByUsername(resetPasswordInfo.getPhone());
        if (user == null) {
            throw new PzException("用户不存在");
        } else {
            user.setPassword(passwordEncoder.encode(resetPasswordInfo.getPassword()));
        }
    }

	@Override
	public void changePoint(Long id, int amount, String type) throws Exception {
		User user = userRepository.findOne(id);
		user.setPoint(user.getPoint() + amount);
		
		weixinService.pushTemplateMessage(buildPointMessage(user, amount, type));
	}
	


	/**
	 * 构建积分变化的模板消息
	 * @param scaner
	 * @param sender
	 * @return
	 * @since 2016年5月9日
	 */
	private TemplateMessage buildPointMessage(User user, Integer amount, String type) {
//		String appName = paramService.getParam("app.name").getValue();
		TemplateMessage templateMessage = new TemplateMessage(user.getWeixinOpenId(), pointChangeMessageTemplateId);
		templateMessage.addValue("first", "您好，由于您对美丽马驹桥活动的支持，给您个人账号增加鲜花积分如下:");
		templateMessage.addValue("keyword1", type);
		templateMessage.addValue("keyword2", "新增鲜花积分"+amount+"分，累计鲜花积分"+user.getPoint()+"分");
		templateMessage.addValue("keyword3", "线上获得");
		templateMessage.addValue("remark", "美丽城市，全民共建！");
		return templateMessage;
	}

}
