/*
 * 项目名称：mirage-clearing
 * 类名称: Profit
 * 创建时间: 2016年9月5日 上午9:32:42
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

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * 在结算体系里产生的利润
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
public class Profit extends DomainImpl {
    
    /**
     * 利润所属的用户
     */
    private Long userId;
    /**
     * 历史产生的总利润 
     */
    private BigDecimal total;
    /**
     * 可用利润
     */
    private BigDecimal available;
    /**
     * 待发放利润
     */
    private BigDecimal pending;
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
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    /**
     * @return the available
     */
    public BigDecimal getAvailable() {
        return available;
    }
    /**
     * @param available the available to set
     */
    public void setAvailable(BigDecimal available) {
        this.available = available;
    }
    /**
     * @return the pending
     */
    public BigDecimal getPending() {
        return pending;
    }
    /**
     * @param pending the pending to set
     */
    public void setPending(BigDecimal pending) {
        this.pending = pending;
    }
    
}
