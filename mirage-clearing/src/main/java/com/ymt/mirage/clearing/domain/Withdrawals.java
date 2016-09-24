/*
 * 项目名称：mirage-clearing
 * 类名称: Withdrawals
 * 创建时间: 2016年9月24日 下午3:00:36
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * 提现申请
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
public class Withdrawals extends DomainImpl {
    
    /**
     * 提现人
     */
    @ManyToOne
    private User user;
    /**
     * 提现金额
     */
    private BigDecimal amount;
    /**
     * 申请状态
     */
    @Enumerated
    private WithdrawalsState state;
    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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

}
