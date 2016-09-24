/*
 * 项目名称：mirage-lesson
 * 类名称: ProductInfo
 * 创建时间: 2016年9月24日 下午3:55:52
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.dto;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class ProductInfo {
    
    private Long id;
    
    private String name;
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

}
