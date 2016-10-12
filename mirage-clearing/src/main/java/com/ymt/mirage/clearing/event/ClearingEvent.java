/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingEvent
 * 创建时间: 2016年10月10日 下午3:06:18
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.event;

import java.math.BigDecimal;

import org.springframework.context.ApplicationEvent;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class ClearingEvent extends ApplicationEvent {
    /**
     * 
     */
    private static final long serialVersionUID = 2261291874680623642L;
    
    /**
     * 贡献者
     */
    private Long contributorId;
    /**
     * 获益者
     */
    private Long beneficiaryId;
    /**
     * 商品id
     */
    private Long orderId;
    /**
     * 收益
     */
    private BigDecimal profit;

    public ClearingEvent(Object source) {
        super(source);
    }

    /**
     * @return the contributorId
     */
    public Long getContributorId() {
        return contributorId;
    }

    /**
     * @param contributorId the contributorId to set
     */
    public void setContributorId(Long contributorId) {
        this.contributorId = contributorId;
    }

    /**
     * @return the beneficiaryId
     */
    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    /**
     * @param beneficiaryId the beneficiaryId to set
     */
    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    /**
     * @return the productId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param productId the productId to set
     */
    public void setOrderId(Long productId) {
        this.orderId = productId;
    }

    /**
     * @return the profit
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

}
