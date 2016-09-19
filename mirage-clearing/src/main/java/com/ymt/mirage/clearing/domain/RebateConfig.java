/*
 * 项目名称：mirage-clearing
 * 类名称: RebateConfig
 * 创建时间: 2016年9月5日 上午10:03:35
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
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
public class RebateConfig extends DomainImpl {
    
    /**
     * 级数
     */
    private int level;
    /**
     * 获利百分比
     */
    private BigDecimal percentage;
    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }
    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }
    /**
     * @return the percentage
     */
    public BigDecimal getPercentage() {
        return percentage;
    }
    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
    
}
