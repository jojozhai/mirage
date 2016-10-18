/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingTreeSpec
 * 创建时间: 2016年10月13日 下午10:14:24
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.repository.spec;

import com.ymt.mirage.clearing.domain.ClearingTree;
import com.ymt.mirage.clearing.dto.ClearingTreeInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class ClearingTreeSpec extends PzSimpleSpecification<ClearingTree, ClearingTreeInfo> {

    public ClearingTreeSpec(ClearingTreeInfo condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<ClearingTree> queryWraper) {
        addLikeCondition(queryWraper, "userNickname", "user.nickname");
    }

}
