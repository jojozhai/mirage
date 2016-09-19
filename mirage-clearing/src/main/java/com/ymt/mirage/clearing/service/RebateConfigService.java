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

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface RebateConfigService {

    BigDecimal getRebatePercentage(int level);

}
