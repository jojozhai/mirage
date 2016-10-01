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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.clearing.domain.RebateConfig;
import com.ymt.mirage.clearing.dto.RebateConfigInfo;
import com.ymt.mirage.clearing.repository.RebateConfigRepository;
import com.ymt.mirage.clearing.repository.spec.RebateConfigSpec;
import com.ymt.mirage.clearing.service.RebateConfigService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

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
            return BigDecimal.ZERO;
        }
        return rebate.getPercentage();
    }
    
    @Override
    public Page<RebateConfigInfo> query(RebateConfigInfo rebateConfigInfo, Pageable pageable) {
        Page<RebateConfig> pageData = rebateConfigRepository.findAll(new RebateConfigSpec(rebateConfigInfo), pageable);
        return QueryResultConverter.convert(pageData, RebateConfigInfo.class, pageable);
    }

    @Override
    public RebateConfigInfo create(RebateConfigInfo rebateConfigInfo) {
        RebateConfig rebateConfig = new RebateConfig();
        BeanUtils.copyProperties(rebateConfigInfo, rebateConfig);
        rebateConfigInfo.setId(rebateConfigRepository.save(rebateConfig).getId());
        return rebateConfigInfo;
    }

    @Override
    public RebateConfigInfo getInfo(Long id) {
        RebateConfig rebateConfig = rebateConfigRepository.findOne(id);
        RebateConfigInfo info = new RebateConfigInfo();
        BeanUtils.copyProperties(rebateConfig, info);
        return info;
    }

    @Override
    public RebateConfigInfo update(RebateConfigInfo rebateConfigInfo) {
        RebateConfig rebateConfig = rebateConfigRepository.findOne(rebateConfigInfo.getId());
        BeanUtils.copyProperties(rebateConfigInfo, rebateConfig);
        rebateConfigRepository.save(rebateConfig);
        return rebateConfigInfo;
    }

    @Override
    public void delete(Long id) {
        rebateConfigRepository.delete(id);       
    }

}
