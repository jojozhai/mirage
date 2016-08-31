/**
 * 
 */
package com.ymt.mirage.poster.dto;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public class PosterInfo {

	private Long id;
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
	private Integer headTop;
	/**
	 * 头像left
	 */
	private Integer headLeft;
	/**
	 * 头像缩放比例
	 */
	private Integer headScale;
	/**
	 * 二维码top 
	 */
	private Integer qrcodeTop;
	/**
	 * 二维码left
	 */
	private Integer qrcodeLeft;
	/**
	 * 二维码缩放比例
	 */
	private Integer qrcodeScale;
	/**
	 * 生成后提示文案
	 */
	private String generatedTip;
	/**
	 * 只有新用户扫码时才加积分 
	 */
	private Boolean onlyNewUserAddPoint;
	/**
	 * 内切圆头像
	 */
	private Boolean incircleHead;
	/**
	 * 多少积分才可以激活这个海报的事件
	 */
	private Integer activePoint;
	/**
	 * 激活以后的提示信息
	 */
	private String activeTip;
	/**
	 * 点击激活消息后跳转的页面
	 */
	private String activeUrl;
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
	public Integer getHeadTop() {
		return headTop;
	}
	/**
	 * @param headTop the headTop to set
	 */
	public void setHeadTop(Integer headTop) {
		this.headTop = headTop;
	}
	/**
	 * @return the headLeft
	 */
	public Integer getHeadLeft() {
		return headLeft;
	}
	/**
	 * @param headLeft the headLeft to set
	 */
	public void setHeadLeft(Integer headLeft) {
		this.headLeft = headLeft;
	}
	/**
	 * @return the qrcodeTop
	 */
	public Integer getQrcodeTop() {
		return qrcodeTop;
	}
	/**
	 * @param qrcodeTop the qrcodeTop to set
	 */
	public void setQrcodeTop(Integer qrcodeTop) {
		this.qrcodeTop = qrcodeTop;
	}
	/**
	 * @return the qrcodeLeft
	 */
	public Integer getQrcodeLeft() {
		return qrcodeLeft;
	}
	/**
	 * @param qrcodeLeft the qrcodeLeft to set
	 */
	public void setQrcodeLeft(Integer qrcodeLeft) {
		this.qrcodeLeft = qrcodeLeft;
	}
	/**
	 * @return the generatedTip
	 */
	public String getGeneratedTip() {
		return generatedTip;
	}
	/**
	 * @param generatedTip the generatedTip to set
	 */
	public void setGeneratedTip(String generatedTip) {
		this.generatedTip = generatedTip;
	}
	/**
	 * @return the headScale
	 */
	public Integer getHeadScale() {
		return headScale;
	}
	/**
	 * @param headScale the headScale to set
	 */
	public void setHeadScale(Integer headScale) {
		this.headScale = headScale;
	}
	/**
	 * @return the qrcodeScale
	 */
	public Integer getQrcodeScale() {
		return qrcodeScale;
	}
	/**
	 * @param qrcodeScale the qrcodeScale to set
	 */
	public void setQrcodeScale(Integer qrcodeScale) {
		this.qrcodeScale = qrcodeScale;
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
	 * @return the onlyNewUserAddPoint
	 */
	public Boolean getOnlyNewUserAddPoint() {
		return onlyNewUserAddPoint;
	}
	/**
	 * @param onlyNewUserAddPoint the onlyNewUserAddPoint to set
	 */
	public void setOnlyNewUserAddPoint(Boolean onlyNewUserAddPoint) {
		this.onlyNewUserAddPoint = onlyNewUserAddPoint;
	}
	/**
	 * @return the incircleHead
	 */
	public Boolean getIncircleHead() {
		return incircleHead;
	}
	/**
	 * @param incircleHead the incircleHead to set
	 */
	public void setIncircleHead(Boolean incircleHead) {
		this.incircleHead = incircleHead;
	}
	/**
	 * @return the activePoint
	 */
	public Integer getActivePoint() {
		return activePoint;
	}
	/**
	 * @param activePoint the activePoint to set
	 */
	public void setActivePoint(Integer activePoint) {
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
	
}
