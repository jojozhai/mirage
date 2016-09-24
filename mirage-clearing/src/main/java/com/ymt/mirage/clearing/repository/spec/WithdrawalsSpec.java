/*
 * 项目名称：mirage-clearing
 * 类名称: WithdrawalsSpec
 * 创建时间: 2016年9月24日 下午3:06:35
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.repository.spec;

import com.ymt.mirage.clearing.domain.Withdrawals;
import com.ymt.mirage.clearing.dto.WithdrawalsInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class WithdrawalsSpec extends PzSimpleSpecification<Withdrawals, WithdrawalsInfo> {

    public WithdrawalsSpec(WithdrawalsInfo condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<Withdrawals> queryWraper) {
        addLikeCondition(queryWraper, "username", "user.nickname");
    }

}
