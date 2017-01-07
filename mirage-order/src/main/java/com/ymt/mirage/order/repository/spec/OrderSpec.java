/*
 * 项目名称：mirage-order
 * 类名称: OrderSpec
 * 创建时间: 2016年9月27日 上午11:03:38
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.repository.spec;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.ymt.mirage.order.domain.Order;
import com.ymt.mirage.order.dto.OrderInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class OrderSpec extends PzSimpleSpecification<Order, OrderInfo> {

    public OrderSpec(OrderInfo condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<Order> queryWraper) {
        
        addLikeCondition(queryWraper, "name");
        addLikeCondition(queryWraper, "userRealname", "user.realname");
        addLikeCondition(queryWraper, "userNickname", "user.nickname");
        addLikeCondition(queryWraper, "userMobile", "user.mobile");
        addEqualsCondition(queryWraper, "state");
        
        addInConditionToColumn(queryWraper, "state", getCondition().getStates());
        
        addBetweenCondition(queryWraper, "amount");
        
        if(StringUtils.isNotBlank(getCondition().getExportDate())) {
            Date endDate = new Date();
            Date startDate = new DateTime().plusDays(0 - new Integer(getCondition().getExportDate())).toDate();
            addBetweenConditionToColumn(queryWraper, "createdTime", startDate, endDate);
        }
        
    }

}
