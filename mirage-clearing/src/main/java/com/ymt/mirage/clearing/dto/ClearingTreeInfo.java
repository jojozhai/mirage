/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingTreeInfo
 * 创建时间: 2016年10月13日 下午10:14:48
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.dto;

import com.ymt.mirage.user.dto.UserInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class ClearingTreeInfo {
    
    private Long id;
    
    private String userNickname;
    
    private String parentNickname;
    
    private UserInfo parentInfo;
    
    private UserInfo userInfo;

    /**
     * @return the parentInfo
     */
    public UserInfo getParentInfo() {
        return parentInfo;
    }

    /**
     * @param parentInfo the parentInfo to set
     */
    public void setParentInfo(UserInfo parentInfo) {
        this.parentInfo = parentInfo;
    }

    /**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the userNickname
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * @param userNickname the userNickname to set
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * @return the parentNickname
     */
    public String getParentNickname() {
        return parentNickname;
    }

    /**
     * @param parentNickname the parentNickname to set
     */
    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }
    
}
