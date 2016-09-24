/*
 * 项目名称：mirage-clearing
 * 类名称: WithdrawalsInfo
 * 创建时间: 2016年9月24日 下午3:03:19
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.dto;

import java.math.BigDecimal;

import com.ymt.mirage.clearing.domain.WithdrawalsState;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class WithdrawalsInfo {
    
    private Long id;
    /**
     * 提现人
     */
    private Long userId;
    /**
     * 提现人姓名
     */
    private String username;
    /**
     * 提现金额
     */
    private BigDecimal amount;
    /**
     * 申请状态
     */
    private WithdrawalsState state;
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
    public WithdrawalsState getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(WithdrawalsState state) {
        this.state = state;
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

}
