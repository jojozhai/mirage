/*
 * 项目名称：mirage-order
 * 类名称: OrderWeixinController
 * 创建时间: 2016年9月20日 下午2:34:01
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.web.controller.weixin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.order.dto.OrderInfo;
import com.ymt.mirage.order.dto.OrderViewInfo;
import com.ymt.mirage.order.service.OrderService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;
import com.ymt.pz365.framework.weixin.pay.JsapiPaymentInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@RestController
@Profile({"weixin","app"})
public class OrderWeixinController {
    
    @Autowired
    private OrderService orderService;
    
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public JsapiPaymentInfo create(@RequestBody OrderInfo orderInfo, HttpServletRequest request) throws Exception {
        orderInfo.setUserId(CurrentUserHolder.getCurrentUserId());
        return orderService.create(orderInfo, request.getRemoteAddr());
    }
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public Page<OrderViewInfo> query(@RequestParam(defaultValue = "false") Boolean finish, Pageable pageable) throws Exception {
        return orderService.query(CurrentUserHolder.getCurrentUserId(), finish, pageable);
    }
    
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public void confirm(@PathVariable Long id) throws Exception {
        orderService.confirm(id);
    }

}
