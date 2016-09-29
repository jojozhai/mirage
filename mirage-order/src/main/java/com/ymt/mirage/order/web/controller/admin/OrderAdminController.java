/**
 * 
 */
package com.ymt.mirage.order.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.order.dto.OrderInfo;
import com.ymt.mirage.order.service.OrderService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class OrderAdminController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public Page<OrderInfo> query(OrderInfo orderInfo, Pageable pageable) {
		return orderService.query(orderInfo, pageable);
	}
	
}
