/*
 * 项目名称：mirage-order
 * 类名称: OrderStateChangeEvent
 * 创建时间: 2016年10月6日 下午3:56:31
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.event;

import org.springframework.context.ApplicationEvent;

import com.ymt.mirage.order.domain.OrderState;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class OrderStateChangeEvent extends ApplicationEvent {
    
    /**
     * 
     */
    private static final long serialVersionUID = -220229780500309391L;
    private Long orderId;
    private OrderState fromState;
    private OrderState toState;
    
    public OrderStateChangeEvent(Long orderId) {
        super(orderId);
        this.orderId = orderId;
    }

    /**
     * @return the orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the fromState
     */
    public OrderState getFromState() {
        return fromState;
    }

    /**
     * @param fromState the fromState to set
     */
    public void setFromState(OrderState fromState) {
        this.fromState = fromState;
    }

    /**
     * @return the toState
     */
    public OrderState getToState() {
        return toState;
    }

    /**
     * @param toState the toState to set
     */
    public void setToState(OrderState toState) {
        this.toState = toState;
    }
    
}
