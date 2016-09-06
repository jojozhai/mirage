/*
 * 项目名称：mirage-lesson
 * 类名称: LessonTag
 * 创建时间: 2016年9月2日 上午9:14:06
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

import com.ymt.mirage.tag.domain.Tag;
import com.ymt.mirage.tag.domain.TagRelation;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
public class LessonSet extends DomainImpl implements TagRelation<Lesson> {

    /**
     * 
     */
    @ManyToOne
    private Lesson target;
    /**
     * 
     */
    @ManyToOne
    private Tag tag;
    /**
     * @return the lesson
     */
    public Lesson getTarget() {
        return target;
    }
    /**
     * @param lesson the lesson to set
     */
    public void setTarget(Lesson lesson) {
        this.target = lesson;
    }
    /**
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }
    /**
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }
    
}
