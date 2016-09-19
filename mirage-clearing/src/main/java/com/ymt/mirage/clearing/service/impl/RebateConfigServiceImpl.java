/*
 * 项目名称：mirage-clearing
 * 类名称: RebateConfigServiceImpl
 * 创建时间: 2016年9月5日 下午2:05:32
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

import com.ymt.mirage.clearing.domain.RebateConfig;
import com.ymt.mirage.clearing.repository.RebateConfigRepository;
import com.ymt.mirage.clearing.service.RebateConfigService;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("rebateConfigService")
@Transactional
public class RebateConfigServiceImpl implements RebateConfigService {
    
    @Autowired
    private RebateConfigRepository rebateConfigRepository;

    /* (non-Javadoc)
     * @see com.ymt.mirage.clearing.service.RebateConfigService#getRebatePercentage(int)
     */
    @Override
    public BigDecimal getRebatePercentage(int level) {
        RebateConfig rebate = rebateConfigRepository.findByLevel(level);
        if(rebate == null) {
            throw new PzException("无法找到第"+level+"级返利配置");
        }
        return rebate.getPercentage();
    }

}
