/*
 * 项目名称：mirage-order
 * 类名称: OrderViewInfo
 * 创建时间: 2016年9月20日 下午4:39:12
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.dto;

import com.ymt.mirage.order.domain.OrderState;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class OrderViewInfo {
    
    private Long id;
    
    private OrderState state;
    
    private String productName;
    
    private String teacherName;
    
    private String teacherTitle;
    
    private String teacherImage;
    
    private int saleCount;

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
     * @return the lessonName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param lessonName the lessonName to set
     */
    public void setProductName(String lessonName) {
        this.productName = lessonName;
    }

    /**
     * @return the teacherName
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * @param teacherName the teacherName to set
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * @return the teacherTitle
     */
    public String getTeacherTitle() {
        return teacherTitle;
    }

    /**
     * @param teacherTitle the teacherTitle to set
     */
    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    /**
     * @return the teacherImage
     */
    public String getTeacherImage() {
        return teacherImage;
    }

    /**
     * @param teacherImage the teacherImage to set
     */
    public void setTeacherImage(String teacherImage) {
        this.teacherImage = teacherImage;
    }

    /**
     * @return the state
     */
    public OrderState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(OrderState state) {
        this.state = state;
    }

    /**
     * @return the saleCount
     */
    public int getSaleCount() {
        return saleCount;
    }

    /**
     * @param saleCount the saleCount to set
     */
    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }
    
}
