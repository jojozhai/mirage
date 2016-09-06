/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingService
 * 创建时间: 2016年9月5日 上午10:13:16
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.service;

import com.ymt.mirage.clearing.domain.Clearingable;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface ClearingService {
    
    /**
     * 针对某一类结算物，将某个用户加入该类结算物的结算树。
     * 
     * @param identify 结算物标识
     * @param userId 新用户id
     * @param name 新用户姓名
     * @param parentUserId 上级用户id 
     * @author zhailiang
     * @since 2016年9月5日
     */
    void addUser(String identify, Long userId, String name, Long parentUserId);
    
    /**
     * 结算
     * @param userId 针对哪个用户来结算
     * @param clearingable 可结算物
     * @author zhailiang
     * @since 2016年9月5日
     */
    void clearing(Clearingable clearingable);

}
