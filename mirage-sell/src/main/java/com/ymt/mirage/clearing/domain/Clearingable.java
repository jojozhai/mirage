/*
 * 项目名称：mirage-sell
 * 类名称: Clearingable
 * 创建时间: 2016年9月5日 上午9:25:49
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.domain;

import java.math.BigDecimal;

import com.ymt.pz365.data.jpa.domain.Domain;

/**
 * 可结算物，实现此接口的对象可以进入结算体系进行结算
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface Clearingable extends Domain{
    
    /**
     * 获取产生可结算物的用户的id,这里获取到的用户id会作为结算流水中贡献者的id存储
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    Long getCreaterId();
    /**
     * 获取产生可结算物的用户的姓名
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    String getCreaterName();
    /**
     * 获取可结算物的类型，同一类型的可结算物会形成一棵结算树。
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    String getIdentify();
    /**
     * 获取结算类型
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    ClearingType getType();
    /**
     * 获取可结算物的价值
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    BigDecimal getValue();
    /**
     * 获取可结算物的名称
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    String getName();

}
