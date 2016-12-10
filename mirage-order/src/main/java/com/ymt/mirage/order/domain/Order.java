/*
 * 项目名称：mirage-order
 * 类名称: Order
 * 创建时间: 2016年9月20日 下午2:24:07
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.ClearingType;
import com.ymt.pz365.data.jpa.domain.Clearingable;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Order extends DomainImpl implements Clearingable {
    
    /**
     * 名称
     */
    private String name;
    /**
     * 订单号
     */
    private String number;
    /**
     * 用户id
     */
    @ManyToOne
    private User user;
    /**
     * 分享者
     */
    @ManyToOne
    private User sharer;
    /**
     * 订单金额
     */
    private BigDecimal amount;
    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    private OrderState state;
    /**
     * 订单产品
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderGoods> products = new ArrayList<OrderGoods>();
    /**
     * 留言
     */
    @Column(length = 2000)
    private String message;
    /**
     * 
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date completeTime;
    
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
     * @return the products
     */
    public List<OrderGoods> getProducts() {
        return products;
    }
    /**
     * @param products the products to set
     */
    public void setProducts(List<OrderGoods> products) {
        this.products = products;
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
     * @return the sharer
     */
    public User getSharer() {
        return sharer;
    }
    /**
     * @param sharer the sharer to set
     */
    public void setSharer(User sharer) {
        this.sharer = sharer;
    }
    @Override
    public Long getCreaterId() {
        return getUser().getId();
    }
    @Override
    public String getCreaterName() {
        return getUser().getNickname();
    }
    @Override
    public String getIdentify() {
        return getId().toString();
    }
    @Override
    public ClearingType getType() {
        return ClearingType.REBATE;
    }
    @Override
    public BigDecimal getValue() {
        return getAmount();
    }
    @Override
    public String getName() {
        return "order:"+getId();
    }
    /**
     * @return the completeTime
     */
    public Date getCompleteTime() {
        return completeTime;
    }
    /**
     * @param completeTime the completeTime to set
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
