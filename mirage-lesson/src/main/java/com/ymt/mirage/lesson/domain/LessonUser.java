/*
 * 项目名称：mirage-lesson
 * 类名称: LessonUser
 * 创建时间: 2016年9月19日 下午3:31:16
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
public class LessonUser extends DomainImpl {

    @ManyToOne
    private Lesson lesson;
    
    @ManyToOne
    private User user;
    
    private boolean online;

    /**
     * @return the lesson
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * @param lesson the lesson to set
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the online
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * @param online the online to set
     */
    public void setOnline(boolean online) {
        this.online = online;
    }
    
}
