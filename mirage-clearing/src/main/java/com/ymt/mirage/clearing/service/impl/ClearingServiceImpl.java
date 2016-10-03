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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.clearing.domain.Clearing;
import com.ymt.mirage.clearing.domain.ClearingTree;
import com.ymt.mirage.clearing.domain.Profit;
import com.ymt.mirage.clearing.repository.ClearingTreeRepository;
import com.ymt.mirage.clearing.service.ClearingService;
import com.ymt.mirage.clearing.service.ProfitService;
import com.ymt.mirage.clearing.service.RebateConfigService;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.domain.Clearingable;
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
    
    /**
     * 默认的根节点用户id
     */
    public static final Long DEFAULT_ROOT_USER_ID = 0L; 
    
    @Autowired
    private RebateConfigService rebateConfigService;
    
    @Autowired
    private ProfitService profitService;
    
    @Autowired
    private ClearingTreeRepository clearingTreeRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderGoodsService orderGoodsService;

    /* (non-Javadoc)
     * @see com.ymt.mirage.clearing.service.ClearingService#addUser(java.lang.String, java.lang.Long, java.lang.String, java.lang.Long)
     */
    @Override
    public void addUser(String identify, Long userId, Long sharerId) {
        
        System.out.println("clearing add User identify:"+identify);
        System.out.println("clearing add User userId:"+userId);
        System.out.println("clearing add User sharerId:"+sharerId);
        
        if(userId.equals(sharerId)) {
            return;
        }
        
        if(orderGoodsService.getGoodsInfo(new Long(identify)).isKey()){
            
            identify = Clearing.TARGET_ID;
            
            ClearingTree node = clearingTreeRepository.findByIdentifyAndUserId(identify, userId);
            
            if(node == null) {
                
                if(sharerId == null) {
                    sharerId = DEFAULT_ROOT_USER_ID;
                }
                
                ClearingTree parent = clearingTreeRepository.findByIdentifyAndUserId(identify, sharerId);
                if(parent == null) {
                    if(DEFAULT_ROOT_USER_ID == sharerId) {
                        parent = createClearingTreeNode(identify, DEFAULT_ROOT_USER_ID, null);
                    }else{
                        throw new PzException("父用户id不存在:"+sharerId+", type:"+identify);
                    }
                }
                createClearingTreeNode(identify, userId, parent);
                
//            }else{
//                throw new PzException("用户'"+userId+"'已经存在于类型为'"+identify+"'的结算树中，父节点为:"+node.getParent().getName()+"("+node.getParent().getId()+")");
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
        return clearingTreeRepository.save(node);
    }

    /* (non-Javadoc)
     * @see com.ymt.mirage.clearing.service.ClearingService#clearing(java.lang.Long, com.ymt.mirage.clearing.domain.Clearingable)
     */
    @Override
    public void clearing(Clearingable clearingable) {
        
        String identify = clearingable.getIdentify();
        Long createrId = clearingable.getCreaterId();
        
        ClearingTree node = clearingTreeRepository.findByIdentifyAndUserId(identify, createrId);
        if(node == null) {
            throw new PzException("可结算物创建者:"+clearingable.getCreaterName()+"("+clearingable.getCreaterId()+")"+"不在结算物类型'"+identify+"'的结算树中");
        }
        
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
        
        Profit profit = profitService.findByUserId(node.getUser().getId());
        
        boolean reduceProfit = clearingable.getType().isReduceProfit();
        BigDecimal percentage = rebateConfigService.getRebatePercentage(level);
        if(percentage.equals(BigDecimal.ZERO)) {
            return;
        }
        BigDecimal profitAmount = clearingable.getValue().multiply(percentage.divide(new BigDecimal(100), 4, RoundingMode.HALF_UP));
        BigDecimal newAvailableAmount = reduceProfit?profit.getAvailable().subtract(profitAmount):profit.getAvailable().add(profitAmount);
        profit.setAvailable(newAvailableAmount);
        
        node.getUser().setMoney(newAvailableAmount);
        
        Clearing clearing = new Clearing();
        clearing.setAfter(newAvailableAmount);
        clearing.setAmount(profitAmount);
        clearing.setBefore(profit.getAvailable());
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
        
        if(node.getParent() != null) {
            clearing(clearingable, node.getParent(), level + 1);
        }
            
    }

}
