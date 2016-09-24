/*
 * 项目名称：mirage-lesson
 * 类名称: LessonCreatedEvent
 * 创建时间: 2016年9月24日 下午3:56:49
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.event;

import org.springframework.context.ApplicationEvent;

import com.ymt.mirage.lesson.dto.LessonInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class LessonInfoChangedEvent extends ApplicationEvent {

    /**
     * 
     */
    private static final long serialVersionUID = -6455489597098026135L;

    public LessonInfoChangedEvent(Object source) {
        super(source);
        this.lessonInfo = (LessonInfo)source;
    }
    
    private LessonInfo lessonInfo;

    /**
     * @return the lessonInfo
     */
    public LessonInfo getLessonInfo() {
        return lessonInfo;
    }


    /**
     * @param lessonInfo the lessonInfo to set
     */
    public void setLessonInfo(LessonInfo lessonInfo) {
        this.lessonInfo = lessonInfo;
    }


    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
