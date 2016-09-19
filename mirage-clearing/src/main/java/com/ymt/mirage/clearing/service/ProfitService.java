/*
 * 项目名称：mirage-clearing
 * 类名称: ProfitService
 * 创建时间: 2016年9月5日 上午11:18:31
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.service;

import com.ymt.mirage.clearing.domain.Profit;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface ProfitService {

    /**
     * 根据用户id查找用户目前的利润，如果未找到，则使用该用户id初始化一条用户利润数据。
     * @param id 用户id
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    Profit findByUserId(Long id);

}
