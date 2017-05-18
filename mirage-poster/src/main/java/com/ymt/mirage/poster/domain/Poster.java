/**
 * 
 */
package com.ymt.mirage.poster.domain;

import java.util.Date;

import javax.persistence.Entity;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Entity
public class Poster extends DomainImpl {

	/**
	 * 海报名称
	 */
	private String name;
	/**
	 * 关键字
	 */
	private String key;
	/**
	 * 背景 
	 */
	private String image;
	/**
	 * 头像top
	 */
	private int headTop;
	/**
	 * 头像left
	 */
	private int headLeft;
	/**
	 * 头像缩放比例
	 */
	private int headScale;
	/**
	 * 内切圆头像
	 */
	private boolean incircleHead;
	/**
	 * 二维码top 
	 */
	private int qrcodeTop;
	/**
	 * 二维码left
	 */
	private int qrcodeLeft;
	/**
	 * 二维码缩放比例
	 */
	private int qrcodeScale;
	/**
	 * 生成后提示文案
	 */
	private String generatedTip;
	/**
	 * 只有新用户扫码时才加积分 
	 */
	private boolean onlyNewUserAddPoint;
	/**
	 * 多少积分才可以激活这个海报的事件
	 */
	private int activePoint;
	/**
	 * 激活以后的提示信息
	 */
	private String activeTip;
	/**
	 * 点击激活消息后跳转的页面
	 */
	private String activeUrl;
	
	/**
	 * @param expiredTime
	 * @return
	 * @author zhailiang
	 * @since 2016年5月5日
	 */
	public String getGeneratedTip(Date expiredTime) {
		String expiredTimeString = new DateTime(expiredTime).toString("yyyy-MM-dd HH:mm:ss");
		return String.format(getGeneratedTip(), expiredTimeString);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the headTop
	 */
	public int getHeadTop() {
		return headTop;
	}
	/**
	 * @param headTop the headTop to set
	 */
	public void setHeadTop(int headTop) {
		this.headTop = headTop;
	}
	/**
	 * @return the headLeft
	 */
	public int getHeadLeft() {
		return headLeft;
	}
	/**
	 * @param headLeft the headLeft to set
	 */
	public void setHeadLeft(int headLeft) {
		this.headLeft = headLeft;
	}
	/**
	 * @return the qrcodeTop
	 */
	public int getQrcodeTop() {
		return qrcodeTop;
	}
	/**
	 * @param qrcodeTop the qrcodeTop to set
	 */
	public void setQrcodeTop(int qrcodeTop) {
		this.qrcodeTop = qrcodeTop;
	}
	/**
	 * @return the qrcodeLeft
	 */
	public int getQrcodeLeft() {
		return qrcodeLeft;
	}
	/**
	 * @param qrcodeLeft the qrcodeLeft to set
	 */
	public void setQrcodeLeft(int qrcodeLeft) {
		this.qrcodeLeft = qrcodeLeft;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the generateTip
	 */
	public String getGeneratedTip() {
		return generatedTip;
	}
	/**
	 * @param generateTip the generateTip to set
	 */
	public void setGeneratedTip(String generateTip) {
		this.generatedTip = generateTip;
	}
	/**
	 * @return the headScale
	 */
	public int getHeadScale() {
		return headScale;
	}
	/**
	 * @param headScale the headScale to set
	 */
	public void setHeadScale(int headScale) {
		this.headScale = headScale;
	}
	/**
	 * @return the qrcodeScale
	 */
	public int getQrcodeScale() {
		return qrcodeScale;
	}
	/**
	 * @param qrcodeScale the qrcodeScale to set
	 */
	public void setQrcodeScale(int qrcodeScale) {
		this.qrcodeScale = qrcodeScale;
	}
	/**
	 * @return the onlyNewUserAddPoint
	 */
	public boolean isOnlyNewUserAddPoint() {
		return onlyNewUserAddPoint;
	}
	/**
	 * @param onlyNewUserAddPoint the onlyNewUserAddPoint to set
	 */
	public void setOnlyNewUserAddPoint(boolean onlyNewUserAddPoint) {
		this.onlyNewUserAddPoint = onlyNewUserAddPoint;
	}
	/**
	 * @return the incircleHead
	 */
	public boolean isIncircleHead() {
		return incircleHead;
	}
	/**
	 * @param incircleHead the incircleHead to set
	 */
	public void setIncircleHead(boolean incircleHead) {
		this.incircleHead = incircleHead;
	}
	/**
	 * @return the activePoint
	 */
	public int getActivePoint() {
		return activePoint;
	}
	/**
	 * @param activePoint the activePoint to set
	 */
	public void setActivePoint(int activePoint) {
		this.activePoint = activePoint;
	}
	/**
	 * @return the activeTip
	 */
	public String getActiveTip() {
		return activeTip;
	}
	/**
	 * @param activeTip the activeTip to set
	 */
	public void setActiveTip(String activeTip) {
		this.activeTip = activeTip;
	}
	/**
	 * @return the activeUrl
	 */
	public String getActiveUrl() {
		return activeUrl;
	}
	/**
	 * @param activeUrl the activeUrl to set
	 */
	public void setActiveUrl(String activeUrl) {
		this.activeUrl = activeUrl;
	}
	
	public String getActiveUrl(Long id) {
		return String.format(this.activeUrl, id);
	}
	
}
