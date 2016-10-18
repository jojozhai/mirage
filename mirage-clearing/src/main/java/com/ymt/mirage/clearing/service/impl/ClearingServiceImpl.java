/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingServiceImpl
 * 创建时间: 2016年9月5日 上午10:17:26
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.clearing.domain.Clearing;
import com.ymt.mirage.clearing.domain.ClearingTree;
import com.ymt.mirage.clearing.event.ClearingEvent;
import com.ymt.mirage.clearing.event.ClearingTreeNodeCreatedEvent;
import com.ymt.mirage.clearing.repository.ClearingRepository;
import com.ymt.mirage.clearing.repository.ClearingTreeRepository;
import com.ymt.mirage.clearing.service.ClearingService;
import com.ymt.mirage.clearing.service.RebateConfigService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.domain.Clearingable;
import com.ymt.pz365.data.jpa.domain.Goods;
import com.ymt.pz365.data.jpa.spi.order.OrderGoodsService;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("clearingService")
@Transactional
public class ClearingServiceImpl implements ClearingService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 默认的根节点用户id
     */
    public static final Long DEFAULT_ROOT_USER_ID = 0L; 
    
    @Autowired
    private RebateConfigService rebateConfigService;
    
//    @Autowired
//    private ProfitService profitService;
    
    @Autowired
    private ClearingTreeRepository clearingTreeRepository;
    
    @Autowired
    private ClearingRepository clearingRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderGoodsService orderGoodsService;
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    private Set<Long> ids = new HashSet<>();

    /* (non-Javadoc)
     * @see com.ymt.mirage.clearing.service.ClearingService#addUser(java.lang.String, java.lang.Long, java.lang.String, java.lang.Long)
     */
    @Override
    public void addUser(String identify, Long userId, Long sharerId) {
        addUser(identify, userId, sharerId, false);
    }
    
    @Override
    public void addUser(String identify, Long userId, Long sharerId, boolean buy) {
        logger.info("clearing add User identify:"+identify);
        logger.info("clearing add User userId:"+userId);
        logger.info("clearing add User sharerId:"+sharerId);
        
        if(userId.equals(sharerId)) {
            return;
        }
        
        Goods goods = orderGoodsService.getGoodsInfo(new Long(identify));
        if(goods.isKey() || buy){
            
            identify = Clearing.TARGET_ID;
            
            ClearingTree node = clearingTreeRepository.findByIdentifyAndUserId(identify, userId);
            
            if(node == null) {
                
                if(ids.contains(userId)) {
                    return;
                }else{
                    
                    try {
                        
                        ids.add(userId);
                        
                        if(sharerId == null) {
                            sharerId = DEFAULT_ROOT_USER_ID;
                        }
                        
                        ClearingTree parent = clearingTreeRepository.findByIdentifyAndUserId(identify, sharerId);
                        if(parent == null) {
                            if(DEFAULT_ROOT_USER_ID == sharerId) {
                                parent = createClearingTreeNode(identify, DEFAULT_ROOT_USER_ID, null);
                            }else{
                                if(buy){
                                    if(goods.isKey()){
                                        parent = clearingTreeRepository.findByIdentifyAndUserId(identify, DEFAULT_ROOT_USER_ID);
                                    }else{
                                        return;
                                    }
                                }else{
                                    if(goods.isKey()){
                                        throw new PzException("父用户id不存在:"+sharerId+", type:"+identify);
                                    }else{
                                        return;
                                    }
                                }
                            }
                        }
                        
                        logger.info("add new clearing tree node");
                        ClearingTree newNode =  createClearingTreeNode(identify, userId, parent);
                        
                        applicationEventPublisher.publishEvent(new ClearingTreeNodeCreatedEvent(newNode.getId(), parent.getId()));
                        
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        ids.remove(userId);
                    }
                }
                
            }
        }
        
    }


    /**
     * 创建结算树节点
     * @param identify
     * @param userId
     * @param name
     * @param parent
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    private ClearingTree createClearingTreeNode(String identify, Long userId, ClearingTree parent) {
        ClearingTree node = new ClearingTree();
        node.setIdentify(identify);
        node.setUser(userRepository.getOne(userId));
        node.setParent(parent);
        return clearingTreeRepository.saveAndFlush(node);
    }

    /* (non-Javadoc)
     * @see com.ymt.mirage.clearing.service.ClearingService#clearing(java.lang.Long, com.ymt.mirage.clearing.domain.Clearingable)
     */
    @Override
    public void clearing(Clearingable clearingable) {
        
        String identify = Clearing.TARGET_ID;// clearingable.getIdentify();
        Long createrId = clearingable.getCreaterId();
        
        ClearingTree node = clearingTreeRepository.findByIdentifyAndUserId(identify, createrId);
        
        if(node == null) {
            logger.info("未找到用户"+createrId+"的结算树节点");
            return;
            //throw new PzException("可结算物创建者:"+clearingable.getCreaterName()+"("+clearingable.getCreaterId()+")"+"不在结算物类型'"+identify+"'的结算树中");
        }
        
        logger.info("为结算树节点"+node.getId()+"结算金额");
        
        clearing(clearingable, node.getParent(), 1);
    }

    /**
     * 
     * @param clearingable
     * @param node
     * @param level
     * @author zhailiang
     * @since 2016年9月5日
     */
    private void clearing(Clearingable clearingable, ClearingTree node, int level) {
        
//        Profit profit = profitService.findByUserId(node.getUser().getId());
        
        User user = node.getUser();
        
        boolean reduceProfit = clearingable.getType().isReduceProfit();
        BigDecimal percentage = rebateConfigService.getRebatePercentage(level);
        if(percentage.equals(BigDecimal.ZERO)) {
            return;
        }
        BigDecimal profitAmount = clearingable.getValue().multiply(percentage.divide(new BigDecimal(100), 4, RoundingMode.HALF_UP));
        BigDecimal newAvailableAmount = reduceProfit?user.getMoney().subtract(profitAmount):user.getMoney().add(profitAmount);
//        profit.setAvailable(newAvailableAmount);
        
        Clearing clearing = new Clearing();
        clearing.setAfter(newAvailableAmount);
        clearing.setAmount(profitAmount);
        clearing.setBefore(user.getMoney());
        clearing.setContributor(userRepository.getOne(clearingable.getCreaterId()));
        clearing.setContributorName(clearingable.getCreaterName());
        clearing.setLevel(level);
        clearing.setPercentage(percentage);
        clearing.setTargetId(clearingable.getId());
        clearing.setTargetName(clearingable.getName());
        clearing.setTargetValue(clearingable.getValue());
        clearing.setType(clearingable.getType());
        clearing.setUser(node.getUser());
        clearing.setDetails(clearingable.getType().getDesc(clearing));
        clearingRepository.save(clearing);
        
        user.setMoney(newAvailableAmount);
        
        ClearingEvent event = new ClearingEvent(clearingable);
        event.setContributorId(clearingable.getCreaterId());
        event.setBeneficiaryId(node.getUser().getId());
        event.setOrderId(clearingable.getId());
        event.setProfit(profitAmount);
        applicationEventPublisher.publishEvent(event);
        
        if(node.getParent() != null) {
            clearing(clearingable, node.getParent(), level + 1);
        }
            
    }
}
