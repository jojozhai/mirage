/*
 * 项目名称：mirage-lesson
 * 类名称: WithdrawalsServiceImpl
 * 创建时间: 2016年9月19日 上午10:18:05
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.clearing.domain.Withdrawals;
import com.ymt.mirage.clearing.domain.WithdrawalsState;
import com.ymt.mirage.clearing.dto.WithdrawalsInfo;
import com.ymt.mirage.clearing.repository.WithdrawalsRepository;
import com.ymt.mirage.clearing.repository.spec.WithdrawalsSpec;
import com.ymt.mirage.clearing.service.WithdrawalsService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.core.exception.PzException;
import com.ymt.pz365.framework.weixin.service.WeixinService;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("withdrawalsService")
@Transactional
public class WithdrawalsServiceImpl implements WithdrawalsService {
    
    @Autowired
    private WithdrawalsRepository withdrawalsRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WeixinService weixinService;
    
    @Override
    public Page<WithdrawalsInfo> query(WithdrawalsInfo withdrawalsInfo, Pageable pageable) {
        Page<Withdrawals> pageData = withdrawalsRepository.findAll(new WithdrawalsSpec(withdrawalsInfo), pageable);
        return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Withdrawals, WithdrawalsInfo>() {
            @Override
            protected void doConvert(Withdrawals domain, WithdrawalsInfo info) throws Exception {
                if(domain.getUser() != null) {
                    info.setUserId(domain.getUser().getId());
                    info.setUsername(domain.getUser().getNickname());
                    info.setUserMoney(domain.getUser().getMoney());
                }
            }
        });
    }

    @Override
    public WithdrawalsInfo create(WithdrawalsInfo withdrawalsInfo) {
        
        if(withdrawalsInfo.getAmount().compareTo(new BigDecimal(50)) != 1) {
            throw new PzException("超过50元才可申请提现哦!");
        }
        
        User user = userRepository.findOne(withdrawalsInfo.getUserId());
        if(withdrawalsInfo.getAmount().compareTo(user.getMoney()) != 1) {
            throw new PzException("余额不足!");
        }
        
        Withdrawals withdrawals = new Withdrawals();
        BeanUtils.copyProperties(withdrawalsInfo, withdrawals);
        withdrawals.setState(WithdrawalsState.INIT);
        withdrawals.setUser(userRepository.getOne(withdrawalsInfo.getUserId()));
        withdrawalsInfo.setId(withdrawalsRepository.save(withdrawals).getId());
        return withdrawalsInfo;
    }

    @Override
    public WithdrawalsInfo getInfo(Long id) {
        Withdrawals withdrawals = withdrawalsRepository.findOne(id);
        WithdrawalsInfo info = new WithdrawalsInfo();
        BeanUtils.copyProperties(withdrawals, info);
        return info;
    }
    
    public static void main(String[] args) {
        System.out.println(new BigDecimal(100).compareTo(new BigDecimal(50)));
    }

    @Override
    public WithdrawalsInfo update(WithdrawalsInfo withdrawalsInfo) throws Exception {
        
//        if(withdrawalsInfo.getAmount().compareTo(new BigDecimal(50)) != 1) {
//            throw new PzException("超过50元才可申请提现哦!");
//        }
        
        Withdrawals withdrawals = withdrawalsRepository.findOne(withdrawalsInfo.getId());
        User user = userRepository.findOne(withdrawalsInfo.getUserId());
        if(user.getMoney().compareTo(withdrawals.getAmount()) != 1) {
            throw new PzException("余额不足!");
        }
        
        BigDecimal amount = withdrawals.getAmount().multiply(new BigDecimal(100));
        weixinService.sendRedpack(withdrawals.getId(), withdrawalsInfo.getIp(), user.getWeixinOpenId(), amount.intValue());
        
//      BeanUtils.copyProperties(withdrawalsInfo, withdrawals);
        withdrawals.setState(WithdrawalsState.FINISH); 
        withdrawals.setSendTime(new Date());
        withdrawalsRepository.save(withdrawals);
        return withdrawalsInfo;
    }

    @Override
    public void delete(Long id) {
        withdrawalsRepository.delete(id);       
    }
    
}
