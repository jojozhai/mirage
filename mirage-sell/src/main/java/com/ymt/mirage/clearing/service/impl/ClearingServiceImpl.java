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
import com.ymt.mirage.clearing.domain.Clearingable;
import com.ymt.mirage.clearing.domain.Profit;
import com.ymt.mirage.clearing.repository.ClearingTreeRepository;
import com.ymt.mirage.clearing.service.ClearingService;
import com.ymt.mirage.clearing.service.ProfitService;
import com.ymt.mirage.clearing.service.RebateConfigService;
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

    /* (non-Javadoc)
     * @see com.ymt.mirage.clearing.service.ClearingService#addUser(java.lang.String, java.lang.Long, java.lang.String, java.lang.Long)
     */
    @Override
    public void addUser(String identify, Long userId, String name, Long parentUserId) {
        
        ClearingTree node = clearingTreeRepository.findByIdentifyAndUserId(identify, userId);
        
        if(node == null) {
            
            ClearingTree parent = clearingTreeRepository.findByIdentifyAndUserId(identify, parentUserId);
            if(parent == null) {
                if(DEFAULT_ROOT_USER_ID == parentUserId) {
                    parent = createClearingTreeNode(identify, DEFAULT_ROOT_USER_ID, "root", null);
                }else{
                    throw new PzException("父用户id不存在:"+parentUserId+", type:"+identify);
                }
            }
            createClearingTreeNode(identify, userId, name, parent);
            
        }else{
            
            throw new PzException("用户'"+userId+"'已经存在于类型为'"+identify+"'的结算树中，父节点为:"+node.getParent().getName()+"("+node.getParent().getId()+")");
            
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
    private ClearingTree createClearingTreeNode(String identify, Long userId, String name, ClearingTree parent) {
        ClearingTree node = new ClearingTree();
        node.setIdentify(identify);
        node.setUserId(userId);
        node.setName(name);
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
     * @param clearingable
     * @param node
     * @param level
     * @author zhailiang
     * @since 2016年9月5日
     */
    private void clearing(Clearingable clearingable, ClearingTree node, int level) {
        
        Profit profit = profitService.findByUserId(node.getUserId());
        
        boolean reduceProfit = clearingable.getType().isReduceProfit();
        BigDecimal percentage = rebateConfigService.getRebatePercentage(level);
        BigDecimal profitAmount = clearingable.getValue().multiply(percentage.divide(new BigDecimal(100), 4, RoundingMode.HALF_UP));
        BigDecimal newPendingAmount = reduceProfit?profit.getPending().subtract(profitAmount):profit.getPending().add(profitAmount);
        
        if(!reduceProfit) {
            profit.getTotal().add(profitAmount);
        }
        profit.setPending(newPendingAmount);
        
        Clearing clearing = new Clearing();
        clearing.setAfter(newPendingAmount);
        clearing.setAmount(profitAmount);
        clearing.setBefore(profit.getPending());
        clearing.setContributorId(clearingable.getCreaterId());
        clearing.setContributorName(clearingable.getCreaterName());
        clearing.setLevel(level);
        clearing.setPercentage(percentage);
        clearing.setTargetId(clearingable.getId());
        clearing.setTargetName(clearingable.getName());
        clearing.setTargetValue(clearingable.getValue());
        clearing.setType(clearingable.getType());
        clearing.setUserId(node.getUserId());
        clearing.setDetails(clearingable.getType().getDesc(clearing));
        
        if(node.getParent() != null) {
            clearing(clearingable, node.getParent(), level + 1);
        }
            
    }

}
