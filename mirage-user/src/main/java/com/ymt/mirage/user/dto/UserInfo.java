/**
 * 
 */
package com.ymt.mirage.user.dto;

import java.util.Date;

/**
 * @author zhailiang
 * @since 2016年5月4日
 */
public class UserInfo {
	
	/**
	 * 
	 */
	private Long id;
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
	 * 积分
	 */
	private int point;
	/**
	 * 等级
	 */
	private String level;
	/**
	 * 
	 */
	private String tags;
	/**
	 * 
	 */
	private Date birthday;
	/**
	 * 
	 */
	private Date vipExpired;
	/**
	 * 
	 */
	private boolean vipValid;
	/**
	 * 大咖/vip
	 */
	private boolean vip;
	/**
	 * 
	 */
	private String car;
	/**
	 * @return
	 * @author zhailiang
	 * @since 2016年6月22日
	 */
	private int participationCount;
	
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
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	 * @return the weixinUnionId
	 */
	public String getWeixinUnionId() {
		return weixinUnionId;
	}
	/**
	 * @param weixinUnionId the weixinUnionId to set
	 */
	public void setWeixinUnionId(String weixinUnionId) {
		this.weixinUnionId = weixinUnionId;
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
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the vipValid
	 */
	public boolean isVipValid() {
		return vipValid;
	}
	/**
	 * @param vipValid the vipValid to set
	 */
	public void setVipValid(boolean vipValid) {
		this.vipValid = vipValid;
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
	
}
