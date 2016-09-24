/*
 * 项目名称：mirage-order
 * 类名称: OrderRepository
 * 创建时间: 2016年9月20日 下午2:26:55
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.order.domain.OrderGoods;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Repository
public interface OrderGoodsRepository extends PzRepository<OrderGoods>{

}
