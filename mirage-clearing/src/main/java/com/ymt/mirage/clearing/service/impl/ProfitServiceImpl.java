/*
 * 项目名称：mirage-clearing
 * 类名称: ProfitServiceImpl
 * 创建时间: 2016年9月5日 上午11:20:03
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.clearing.domain.Profit;
import com.ymt.mirage.clearing.repository.ProfitRepository;
import com.ymt.mirage.clearing.service.ProfitService;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("profitService")
@Transactional
public class ProfitServiceImpl implements ProfitService {

    @Autowired
    private ProfitRepository profitRepository;

    /* (non-Javadoc)
     * @see com.ymt.mirage.clearing.service.ProfitService#findByUserId(java.lang.Long)
     */
    @Override
    public Profit findByUserId(Long userId) {
        Profit profit = profitRepository.findByUserId(userId);
        if(profit == null) {
            profit = new Profit();
            profit.setUserId(userId);
            profit.setTotal(BigDecimal.ZERO);
            profit.setAvailable(BigDecimal.ZERO);
            profitRepository.save(profit);
        }
        return profit;
    }

}
