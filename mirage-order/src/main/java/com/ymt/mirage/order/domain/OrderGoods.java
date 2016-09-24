/*
 * 项目名称：mirage-order
 * 类名称: OrderGoods
 * 创建时间: 2016年9月5日 下午3:08:03
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
public class OrderGoods extends DomainImpl {
    
    /**
     * 订单id
     */
    @ManyToOne
    private Order order;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 总数
     */
    private BigDecimal count;
    
    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }
    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }
    /**
     * @return the goodsId
     */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * @param goodsId the goodsId to set
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    /**
     * @return the count
     */
    public BigDecimal getCount() {
        return count;
    }
    /**
     * @param count the count to set
     */
    public void setCount(BigDecimal count) {
        this.count = count;
    }

}
