/*
 * 项目名称：mirage-clearing
 * 类名称: ProfitRepository
 * 创建时间: 2016年9月5日 上午10:23:25
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.clearing.domain.Profit;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Repository
public interface ProfitRepository extends PzRepository<Profit> {

    Profit findByUserId(Long userId);

}
