/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingType
 * 创建时间: 2016年9月5日 上午9:52:10
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.domain;

/**
 * 结算类型，如购物返利，体现等
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public enum ClearingType {
    
    /**
     * 购物返利
     */
    REBATE {
        @Override
        public String getDesc(Clearing clearing) {
            return clearing.getLevel()+"级好友'"+clearing.getContributorName()+"'购买了'"+clearing.getTargetValue()+"',您获得了返利:"+clearing.getAmount()+"元";
        }
    };

    /**
     * 标识结算类型是否减少结算人的利润,默认是false
     * @return
     * @author zhailiang
     * @since 2016年9月5日
     */
    public boolean isReduceProfit() {
        return false;
    }

    public abstract String getDesc(Clearing clearing);
    
}
