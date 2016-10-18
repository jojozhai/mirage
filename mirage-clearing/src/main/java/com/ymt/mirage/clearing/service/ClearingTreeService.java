/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingTreeService
 * 创建时间: 2016年9月5日 上午11:14:14
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.clearing.dto.ClearingTreeInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface ClearingTreeService {

    Page<ClearingTreeInfo> query(ClearingTreeInfo clearingTreeInfo, Pageable pageable);

}
