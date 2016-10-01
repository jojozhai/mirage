/*
 * 项目名称：mirage-clearing
 * 类名称: RebateConfigSpec
 * 创建时间: 2016年9月30日 上午8:51:07
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.repository.spec;

import com.ymt.mirage.clearing.domain.RebateConfig;
import com.ymt.mirage.clearing.dto.RebateConfigInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class RebateConfigSpec extends PzSimpleSpecification<RebateConfig, RebateConfigInfo> {

    public RebateConfigSpec(RebateConfigInfo condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<RebateConfig> queryWraper) {
        addEqualsCondition(queryWraper, "level");
    }

}
