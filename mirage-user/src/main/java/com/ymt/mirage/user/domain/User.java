/**
 * 
 */
package com.ymt.mirage.user.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vdurmont.emoji.EmojiParser;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 *
 */
@Entity
public class User extends DomainImpl implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2059404932715432718L;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 微信号
	 */
	private String weixin;
	/**
	 * 微信openId
	 */
	private String weixinOpenId;
	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	 */
	private String weixinUnionId;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 头像
	 */
	private String headimgurl;
	/**
	 * 生日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	/**
	 * 头像在阿里云上的url;
	 */
	private String headimgOssUrl;
	/**
	 * 内切圆头像在阿里云上的url;
	 */
	private String headimgOssUrlForIncircle;
	/**
	 * 积分
	 */
	private int point;
	/**
	 * 等级
	 */
	private String level;
	/**
	 * 是否是vip
	 */
	private boolean vip;
	/**
	 * vip到期时间
	 */
	private Date vipExpired;
	/**
	 * 标签
	 */
	private String tags;
	/**
	 * 车型
	 */
	private String car;
	/**
	 * 参与活动的次数
	 */
	private int participationCount;
	/**
	 * 是否关注了公众号
	 */
	private int subscribe;
	/**
	 * 职业
	 */
	private String job;
	/**
	 * 年龄 
	 */
	private int age;
	
	/**
	 * 返利钱数
	 */
	private BigDecimal money;
	
	public boolean isRegisted() {
	    return StringUtils.isNotBlank(getRealname()) && StringUtils.isNotBlank(getMobile());
	}

	/**
	 * @return the participationCount
	 */
	public int getParticipationCount() {
		return participationCount;
	}

	/**
	 * @param participationCount the participationCount to set
	 */
	public void setParticipationCount(int participationCount) {
		this.participationCount = participationCount;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
	    if(StringUtils.isNotBlank(nickname)){
	        return EmojiParser.parseToUnicode(nickname);
	    }
		return "";
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
	    if(StringUtils.isNotBlank(nickname)){
	        this.nickname = EmojiParser.parseToAliases(nickname);
	    }else{
	        this.nickname = "";
	    }
	}

	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the weixin
	 */
	public String getWeixin() {
		return weixin;
	}

	/**
	 * @param weixin the weixin to set
	 */
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	/**
	 * @return the weixinOpenId
	 */
	public String getWeixinOpenId() {
		return weixinOpenId;
	}

	/**
	 * @param weixinOpenId the weixinOpenId to set
	 */
	public void setWeixinOpenId(String weixinOpenId) {
		this.weixinOpenId = weixinOpenId;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the headimgurl
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}

	/**
	 * @param headimgurl the headimgurl to set
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * @return the unionid
	 */
	public String getWeixinUnionId() {
		return weixinUnionId;
	}

	/**
	 * @param unionid the unionid to set
	 */
	public void setWeixinUnionId(String unionid) {
		this.weixinUnionId = unionid;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the headimgOssUrl
	 */
	public String getHeadimgOssUrl() {
		return headimgOssUrl;
	}

	/**
	 * @param headimgOssUrl the headimgOssUrl to set
	 */
	public void setHeadimgOssUrl(String headimgOssUrl) {
		this.headimgOssUrl = headimgOssUrl;
	}

	/**
	 * @return the headimgOssUrlForIncircle
	 */
	public String getHeadimgOssUrlForIncircle() {
		return headimgOssUrlForIncircle;
	}

	/**
	 * @param headimgOssUrlForIncircle the headimgOssUrlForIncircle to set
	 */
	public void setHeadimgOssUrlForIncircle(String headimgOssUrlForIncircle) {
		this.headimgOssUrlForIncircle = headimgOssUrlForIncircle;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the vip
	 */
	public boolean isVip() {
		return vip;
	}

	/**
	 * @param vip the vip to set
	 */
	public void setVip(boolean vip) {
		this.vip = vip;
	}

	/**
	 * @return the vipExpired
	 */
	public Date getVipExpired() {
		return vipExpired;
	}

	/**
	 * @param vipExpired the vipExpired to set
	 */
	public void setVipExpired(Date vipExpired) {
		this.vipExpired = vipExpired;
	}

	public boolean isVipValidation() {
	    if(getVipExpired() == null) {
	        return isVip();
	    }else{
	        return isVip() && new DateTime().isBefore(getVipExpired().getTime());
	    }
	}
	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the car
	 */
	public String getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(String car) {
		this.car = car;
	}

    /**
     * @return the subscribe
     */
    public int getSubscribe() {
        return subscribe;
    }

    /**
     * @param subscribe the subscribe to set
     */
    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * @return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

}
