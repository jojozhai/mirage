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
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

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
    
    @Override
    public Page<WithdrawalsInfo> query(WithdrawalsInfo withdrawalsInfo, Pageable pageable) {
        Page<Withdrawals> pageData = withdrawalsRepository.findAll(new WithdrawalsSpec(withdrawalsInfo), pageable);
        return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Withdrawals, WithdrawalsInfo>() {
            @Override
            protected void doConvert(Withdrawals domain, WithdrawalsInfo info) throws Exception {
                info.setUserId(domain.getUser().getId());
                info.setUsername(domain.getUser().getNickname());
            }
        });
    }

    @Override
    public WithdrawalsInfo create(WithdrawalsInfo withdrawalsInfo) {
        Withdrawals withdrawals = new Withdrawals();
        BeanUtils.copyProperties(withdrawalsInfo, withdrawals);
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

    @Override
    public WithdrawalsInfo update(WithdrawalsInfo withdrawalsInfo) {
        Withdrawals withdrawals = withdrawalsRepository.findOne(withdrawalsInfo.getId());
//        BeanUtils.copyProperties(withdrawalsInfo, withdrawals);
        withdrawals.setState(WithdrawalsState.FINISH);
        withdrawalsRepository.save(withdrawals);
        return withdrawalsInfo;
    }

    @Override
    public void delete(Long id) {
        withdrawalsRepository.delete(id);       
    }
    
}
