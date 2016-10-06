/*
 * 项目名称：mirage-order
 * 类名称: OrderService
 * 创建时间: 2016年9月20日 下午2:27:57
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.order.dto.OrderInfo;
import com.ymt.mirage.order.dto.OrderViewInfo;
import com.ymt.pz365.framework.weixin.pay.JsapiPaymentInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface OrderService {

    JsapiPaymentInfo create(OrderInfo orderInfo, String ip) throws Exception;

    Page<OrderViewInfo> query(Long currentUserId, Boolean finish, Pageable pageable);

    void confirm(Long id);

    Page<OrderInfo> query(OrderInfo orderInfo, Pageable pageable);

    OrderInfo update(OrderInfo lessonInfo);

}
