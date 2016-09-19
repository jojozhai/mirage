/*
 * 项目名称：mirage-lesson
 * 类名称: TeacherInfo
 * 创建时间: 2016年9月19日 上午10:16:48
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
public class TeacherInfo {
    
    /**
     * 
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 简介
     */
    private String desc;
    /**
     * 头像
     */
    private String image;
    /**
     * 头衔
     */
    private String title;
    
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
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }
    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
    
}
