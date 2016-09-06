/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingTree
 * 创建时间: 2016年9月5日 上午10:05:45
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.domain;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.ymt.pz365.data.jpa.domain.TreeImpl;

/**
 * 结算树，用来记录某一类结算物中，所有参与结算的用户的关系。
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
@Table(indexes = {
        @Index(columnList = "type"),
        @Index(columnList = "userId")})
public class ClearingTree extends TreeImpl<ClearingTree> {
    
    /**
     * 可结算物的标识。同一标识的可结算物会生成一棵结算树
     */
    private String identify;
    /**
     * 结算用户的id
     */
    private Long userId;
    /**
     * 结算用户的名称
     */
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the identify
     */
    public String getIdentify() {
        return identify;
    }

    /**
     * @param identify the identify to set
     */
    public void setIdentify(String identify) {
        this.identify = identify;
    }


}
