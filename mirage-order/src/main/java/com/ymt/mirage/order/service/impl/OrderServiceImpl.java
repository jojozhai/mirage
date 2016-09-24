/*
 * 项目名称：mirage-order
 * 类名称: OrderServiceImpl
 * 创建时间: 2016年9月20日 下午2:32:53
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.order.domain.Order;
import com.ymt.mirage.order.domain.OrderGoods;
import com.ymt.mirage.order.domain.OrderState;
import com.ymt.mirage.order.dto.CartInfo;
import com.ymt.mirage.order.dto.OrderInfo;
import com.ymt.mirage.order.dto.OrderViewInfo;
import com.ymt.mirage.order.repository.OrderGoodsRepository;
import com.ymt.mirage.order.repository.OrderRepository;
import com.ymt.mirage.order.service.OrderService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.domain.Goods;
import com.ymt.pz365.data.jpa.spi.order.OrderGoodsService;
import com.ymt.pz365.framework.weixin.pay.JsapiPaymentInfo;
import com.ymt.pz365.framework.weixin.pay.UnifiedorderInfo;
import com.ymt.pz365.framework.weixin.service.WeixinService;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderGoodsService orderGoodsService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderGoodsRepository orderGoodsRepository;
    
    @Autowired
    private WeixinService weixinService;

    /* (non-Javadoc)
     * @see com.ymt.mirage.order.service.OrderService#create(com.ymt.mirage.order.dto.OrderInfo)
     */
    @Override
    public JsapiPaymentInfo create(OrderInfo orderInfo, String ip) throws Exception {
        
        User user = userRepository.getOne(orderInfo.getUserId());
        
        Order order = new Order();
        order.setUser(user);
        order.setState(OrderState.INIT);
        order.setMessage(orderInfo.getMessage());
        orderRepository.save(order);
        
        updateUserInfo(orderInfo, user);
        
        BigDecimal amount = BigDecimal.ZERO;
        for (CartInfo cartInfo : orderInfo.getCartInfos()) {
            Goods goods = orderGoodsService.getGoodsInfo(cartInfo.getProductId());
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrder(order);
            orderGoods.setCount(new BigDecimal(cartInfo.getCount()));
            orderGoods.setGoodsId(cartInfo.getProductId());
            orderGoodsRepository.save(orderGoods);
            amount = amount.add(goods.getPrice());
        }
        
        order.setAmount(amount);
        orderRepository.save(order);
        
        UnifiedorderInfo unifiedorderInfo = new UnifiedorderInfo();
        unifiedorderInfo.setAmount(order.getAmount().multiply(new BigDecimal(100)).intValue());
        unifiedorderInfo.setGoodsDesc("订单");
        unifiedorderInfo.setIp(ip);
        unifiedorderInfo.setOpenId(order.getUser().getWeixinOpenId());
        unifiedorderInfo.setOrderId(order.getId());
//      unifiedorderInfo.setAttach(order.getType());
        
//        return weixinService.unifiedorder(unifiedorderInfo);
        return null;
        
    }

    private void updateUserInfo(OrderInfo orderInfo, User user) {
        if(orderInfo.getUserInfo() != null) {
            if(!StringUtils.equals(user.getRealname(), orderInfo.getUserInfo().getRealname())){
                user.setRealname(orderInfo.getUserInfo().getRealname());
            }
            if(!StringUtils.equals(user.getMobile(), orderInfo.getUserInfo().getMobile())){
                user.setMobile(orderInfo.getUserInfo().getMobile());
            }
            if(!StringUtils.equals(user.getProvince(), orderInfo.getUserInfo().getProvince())){
                user.setProvince(orderInfo.getUserInfo().getProvince());
            }
            if(!StringUtils.equals(user.getCity(), orderInfo.getUserInfo().getCity())){
                user.setCity(orderInfo.getUserInfo().getCity());
            }
            userRepository.save(user);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Page<OrderViewInfo> query(Long currentUserId, Boolean finish, Pageable pageable) {
        OrderState[] states = new OrderState[]{OrderState.CANCEL, OrderState.FINISH};
        Page<Order> orderPage;
        if(finish) {
            orderPage = orderRepository.findByUserIdAndStateIn(currentUserId, states, pageable);
        }else{
            orderPage = orderRepository.findByUserIdAndStateNotIn(currentUserId, states, pageable);
        }
        
        List infos = orderGoodsService.convertOrderInfo(orderPage.getContent());
        return new PageImpl(infos, pageable, orderPage.getTotalElements());
    }

    @Override
    public void confirm(Long id) {
        Order order = orderRepository.findOne(id);
        order.setState(OrderState.FINISH);
        orderRepository.save(order);
    }

}
