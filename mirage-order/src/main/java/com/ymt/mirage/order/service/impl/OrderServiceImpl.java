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

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import com.ymt.mirage.order.event.OrderStateChangeEvent;
import com.ymt.mirage.order.repository.OrderGoodsRepository;
import com.ymt.mirage.order.repository.OrderRepository;
import com.ymt.mirage.order.repository.spec.OrderSpec;
import com.ymt.mirage.order.service.OrderService;
import com.ymt.mirage.order.spi.xls.ExcelWriter;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.domain.Goods;
import com.ymt.pz365.data.jpa.spi.order.OrderGoodsService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.core.exception.PzException;
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
    
    @Autowired
    private ExcelWriter<Order> orderExcelWriter;
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /* (non-Javadoc)
     * @see com.ymt.mirage.order.service.OrderService#create(com.ymt.mirage.order.dto.OrderInfo)
     */
    @Override
    public JsapiPaymentInfo create(OrderInfo orderInfo, String ip) throws Exception {
        
        if(orderInfo.getSharerId() == null) {
            orderInfo.setSharerId(0L);
        }
        
        User user = userRepository.getOne(orderInfo.getUserId());
        
        Order order = new Order();
        order.setUser(user);
        order.setSharer(userRepository.getOne(orderInfo.getSharerId()));
        order.setState(OrderState.INIT);
        order.setMessage(orderInfo.getMessage());
        orderRepository.save(order);
        
        updateUserInfo(orderInfo, user);
        
        BigDecimal amount = BigDecimal.ZERO;
        for (CartInfo cartInfo : orderInfo.getCartInfos()) {
            Goods goods = orderGoodsService.getGoodsInfo(cartInfo.getProductId());
            
            order.setName(goods.getName());
            
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
        unifiedorderInfo.setAttach(order.getType().toString());
        
        return weixinService.unifiedorder(unifiedorderInfo);
//      return null;
        
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
        
        OrderState[] finishStates = new OrderState[]{OrderState.CANCEL, OrderState.COMPLETE, OrderState.FINISH};
        OrderState[] unfinishStates = new OrderState[]{OrderState.PAYED, OrderState.WORKING};
        Page<Order> orderPage;
        
        if(finish == null) {
            orderPage = orderRepository.findByUserId(currentUserId, pageable);
        }else{
            if(finish) {
                orderPage = orderRepository.findByUserIdAndStateIn(currentUserId, finishStates, pageable);
            }else{
                orderPage = orderRepository.findByUserIdAndStateIn(currentUserId, unfinishStates, pageable);
            }
        }
        
        List infos = orderGoodsService.convertOrderInfo(orderPage.getContent());
        return new PageImpl(infos, pageable, orderPage.getTotalElements());
    }

    @Override
    public void confirm(Long id) {
        OrderStateChangeEvent orderStateChangeEvent = new OrderStateChangeEvent(id);
        
        Order order = orderRepository.findOne(id);
        
        if(!order.getState().equals(OrderState.WORKING)) {
            throw new PzException("订单状态不正确,只有工作中的订单才可确认完成,当前订单状态:"+order.getState());
        }
        
        orderStateChangeEvent.setFromState(order.getState());
        orderStateChangeEvent.setToState(OrderState.FINISH);
        
        order.setState(OrderState.FINISH);
        orderRepository.save(order);
        
        applicationEventPublisher.publishEvent(orderStateChangeEvent);
    }

    @Override
    public Page<OrderInfo> query(OrderInfo orderInfo, Pageable pageable) {
        Page<Order> pageData = orderRepository.findAll(new OrderSpec(orderInfo), pageable);
        return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Order, OrderInfo>() {
            @Override
            protected void doConvert(Order domain, OrderInfo info) throws Exception {
                UserInfo userInfo = new UserInfo();
                BeanUtils.copyProperties(domain.getUser(), userInfo);
                info.setUserInfo(userInfo);
                info.setGoodsName(orderGoodsService.getGoodsInfo(domain.getProducts().get(0).getGoodsId()).getName());
            }
        });
    }

    @Override
    public OrderInfo update(OrderInfo orderInfo) {
        
        OrderStateChangeEvent orderStateChangeEvent = new OrderStateChangeEvent(orderInfo.getId());
        
        Order order = orderRepository.findOne(orderInfo.getId());
        
        if(orderInfo.getState().equals(OrderState.WORKING)) {
            if(!order.getState().equals(OrderState.PAYED)){
                throw new PzException("订单状态不正确,只有已支付的订单才可接单,当前订单状态:"+order.getState());
            }
        }else if(orderInfo.getState().equals(OrderState.COMPLETE)) {
            if(!order.getState().equals(OrderState.WORKING)){
                throw new PzException("订单状态不正确,只有工作中的订单才可完成,当前订单状态:"+order.getState());
            }
        }
        
        orderStateChangeEvent.setFromState(order.getState());
        orderStateChangeEvent.setToState(orderInfo.getState());
        
        order.setState(orderInfo.getState());
        orderRepository.save(order);
        
        applicationEventPublisher.publishEvent(orderStateChangeEvent);
        
        return orderInfo;
    }

    @Override
    public void export(OrderInfo orderInfo, File file) {
        if(orderInfo.isEmptyForExport()){
            throw new PzException("请至少指定一组查询条件");
        }
        List<Order> data = orderRepository.findAll(new OrderSpec(orderInfo));
        orderExcelWriter.write(data, file);
    }

}
