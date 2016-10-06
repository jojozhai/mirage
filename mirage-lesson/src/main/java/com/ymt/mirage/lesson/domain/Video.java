/*
 * 项目名称：mirage-lesson
 * 类名称: Video
 * 创建时间: 2016年10月5日 上午10:55:57
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.domain;

import javax.persistence.Embeddable;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Embeddable
public class Video {
    
    private Long id;
    
    private String name;
    
    private String link;

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
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
    
}
