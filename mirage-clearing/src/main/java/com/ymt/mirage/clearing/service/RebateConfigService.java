/*
 * 项目名称：mirage-clearing
 * 类名称: RebateConfigService
 * 创建时间: 2016年9月5日 上午11:14:14
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.clearing.dto.RebateConfigInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface RebateConfigService {

    BigDecimal getRebatePercentage(int level);
    
    Page<RebateConfigInfo> query(RebateConfigInfo rebateConfigInfo, Pageable pageable);
    
    RebateConfigInfo create(RebateConfigInfo rebateConfigInfo);

    RebateConfigInfo getInfo(Long id);

    RebateConfigInfo update(RebateConfigInfo rebateConfigInfo);

    void delete(Long id);

}
