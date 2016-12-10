/*
 * 项目名称：mirage-order
 * 类名称: OrderInfo
 * 创建时间: 2016年9月20日 下午2:28:53
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ymt.mirage.order.domain.OrderState;
import com.ymt.mirage.user.dto.UserInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class OrderInfo {
    
    private Long id;
    private Date createdTime;
    private Date createdTimeTo;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String exportDate;
    /**
     * 订单号
     */
    private String number;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String userNickname;
    /**
     * 用户昵称
     */
    private String userRealname;
    /**
     * 用户昵称
     */
    private String userMobile;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 分享者
     */
    private Long sharerId;
    /**
     * 订单金额
     */
    private BigDecimal amount;
    private BigDecimal amountTo;
    /**
     * 订单状态
     */
    private OrderState state;
    /**
     * 商品信息
     */
    private List<CartInfo> cartInfos;
    /**
     * 留言
     */
    private String message;
    /**
     * 用户信息
     */
    private UserInfo userInfo;

    public boolean isEmptyForExport() {
        return StringUtils.isBlank(getExportDate()) && amount == null && amountTo == null && state == null;
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
     * @return the number
     */
    public String getNumber() {
        return number;
    }
    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }
    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**
     * @return the state
     */
    public OrderState getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(OrderState state) {
        this.state = state;
    }
    /**
     * @return the cartInfos
     */
    public List<CartInfo> getCartInfos() {
        return cartInfos;
    }
    /**
     * @param cartInfos the cartInfos to set
     */
    public void setCartInfos(List<CartInfo> cartInfos) {
        this.cartInfos = cartInfos;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }
    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    /**
     * @return the sharerId
     */
    public Long getSharerId() {
        return sharerId;
    }
    /**
     * @param sharerId the sharerId to set
     */
    public void setSharerId(Long sharerId) {
        this.sharerId = sharerId;
    }
    /**
     * @return the userNickname
     */
    public String getUserNickname() {
        return userNickname;
    }
    /**
     * @param userNickname the userNickname to set
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
    /**
     * @return the goodsName
     */
    public String getGoodsName() {
        return goodsName;
    }
    /**
     * @param goodsName the goodsName to set
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    /**
     * @return the createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }
    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    /**
     * @return the userRealname
     */
    public String getUserRealname() {
        return userRealname;
    }
    /**
     * @param userRealname the userRealname to set
     */
    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }
    /**
     * @return the userMobile
     */
    public String getUserMobile() {
        return userMobile;
    }
    /**
     * @param userMobile the userMobile to set
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    /**
     * @return the createdTimeTo
     */
    public Date getCreatedTimeTo() {
        return createdTimeTo;
    }
    /**
     * @param createdTimeTo the createdTimeTo to set
     */
    public void setCreatedTimeTo(Date createdTimeTo) {
        this.createdTimeTo = createdTimeTo;
    }
    /**
     * @return the amountTo
     */
    public BigDecimal getAmountTo() {
        return amountTo;
    }
    /**
     * @param amountTo the amountTo to set
     */
    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }
    /**
     * @return the exportDate
     */
    public String getExportDate() {
        return exportDate;
    }
    /**
     * @param exportDate the exportDate to set
     */
    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
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

}
