/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingTreeNodeCreatedEvent
 * 创建时间: 2016年10月6日 上午10:50:26
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.event;

import org.springframework.context.ApplicationEvent;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class ClearingTreeNodeCreatedEvent extends ApplicationEvent {
    
    private Long userId;
    
    private Long parentId;

    public ClearingTreeNodeCreatedEvent(Long userId, Long parentId) {
        super(userId);
        this.userId = userId;
        this.parentId = parentId;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 5590905288218275551L;

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
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
