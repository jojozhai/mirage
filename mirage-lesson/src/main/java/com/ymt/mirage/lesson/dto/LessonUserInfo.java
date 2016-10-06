/*
 * 项目名称：mirage-lesson
 * 类名称: LessonUserInfo
 * 创建时间: 2016年9月19日 下午3:52:15
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.dto;

import com.ymt.mirage.user.dto.UserInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class LessonUserInfo {
    
    private Long id;
    
    private Long userId;
    
    private Long lessonId;
    
    private String nickname;
    
    private UserInfo userInfo;
    
    private Boolean online;
    
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
     * @return the lessonId
     */
    public Long getLessonId() {
        return lessonId;
    }
    /**
     * @param lessonId the lessonId to set
     */
    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }
    /**
     * @return the online
     */
    public Boolean getOnline() {
        return online;
    }
    /**
     * @param online the online to set
     */
    public void setOnline(Boolean online) {
        this.online = online;
    }
    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }
    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    
}
