/*
 * 项目名称：mirage-clearing
 * 类名称: Clearing
 * 创建时间: 2016年9月5日 上午9:30:13
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.ClearingLog;
import com.ymt.pz365.data.jpa.domain.ClearingType;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * 结算流水
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
//@Table(indexes = {@Index(columnList = "targetId"), @Index(columnList = "userId")})
public class Clearing extends DomainImpl implements ClearingLog {
    
    public static final String TARGET_ID = "jojo";
    
    /**
     * 可结算物id.
     */
    private Long targetId;
    /**
     * 可结算物名称.
     */
    private String targetName;
    /**
     * 结算类型，如购物返现，提现等
     */
    @Enumerated(EnumType.STRING)
    private ClearingType type;
    /**
     * 结算的详细描述，比如：xxx买了xxx,获利xxx元
     */
    private String details;
    /**
     * 流水所属用户id
     */
    @ManyToOne
    private User user;
    /**
     * 产生可结算物的用户的id
     */
    @ManyToOne
    private User contributor;
    /**
     * 产生可结算物的用户的名称
     */
    private String contributorName;
    /**
     * 产生利润的用户相对获得利润的用户的层级数
     */
    private int level;
    /**
     * 产生的利润占可结算物价值的百分比。
     */
    private BigDecimal percentage;
    /**
     * 结算时可结算物的价值
     */
    private BigDecimal targetValue;
    /**
     * 利润变动 
     */
    private BigDecimal amount;
    /**
     * 变动前利润
     */
    private BigDecimal before;
    /**
     * 变动后利润
     */
    private BigDecimal after;
    /**
     * 是否已激活
     */
    private boolean active;
    
    /**
     * @return the targetId
     */
    public Long getTargetId() {
        return targetId;
    }
    /**
     * @param targetId the targetId to set
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
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
     * @return the before
     */
    public BigDecimal getBefore() {
        return before;
    }
    /**
     * @param before the before to set
     */
    public void setBefore(BigDecimal before) {
        this.before = before;
    }
    /**
     * @return the after
     */
    public BigDecimal getAfter() {
        return after;
    }
    /**
     * @param after the after to set
     */
    public void setAfter(BigDecimal after) {
        this.after = after;
    }
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
    /**
     * @return the targetValue
     */
    public BigDecimal getTargetValue() {
        return targetValue;
    }
    /**
     * @param targetValue the targetValue to set
     */
    public void setTargetValue(BigDecimal targetValue) {
        this.targetValue = targetValue;
    }
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
     * @return the contributor
     */
    public User getContributor() {
        return contributor;
    }
    /**
     * @param contributor the contributor to set
     */
    public void setContributor(User contributor) {
        this.contributor = contributor;
    }
    /**
     * @return the type
     */
    public ClearingType getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(ClearingType type) {
        this.type = type;
    }
    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }
    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * @return the targetName
     */
    public String getTargetName() {
        return targetName;
    }
    /**
     * @param targetName the targetName to set
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
    /**
     * @return the contributorName
     */
    public String getContributorName() {
        return contributorName;
    }
    /**
     * @param contributorName the contributorName to set
     */
    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }
    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }
    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
