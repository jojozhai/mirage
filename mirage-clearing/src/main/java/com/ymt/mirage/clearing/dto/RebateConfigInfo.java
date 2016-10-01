/*
 * 项目名称：mirage-clearing
 * 类名称: RebateConfigInfo
 * 创建时间: 2016年9月30日 上午8:49:32
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.dto;

import java.math.BigDecimal;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class RebateConfigInfo {
    
    private Long id;    
    /**
     * 级数
     */
    private Integer level;
    /**
     * 获利百分比
     */
    private BigDecimal percentage;
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
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }
    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
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
