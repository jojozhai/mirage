/*
 * 项目名称：mirage-user
 * 类名称: MessageInfo
 * 创建时间: 2016年10月24日 下午4:03:51
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.user.dto;

import javax.persistence.Lob;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class MessageInfo {
    
    private Long id;
    /**
     * 
     */
    private Long userId;
    /**
     * 
     */
    private Boolean read;
    /**
     * 头像
     */
    private String image;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 内容
     */
    @Lob
    private String content;
    
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
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
     * @return the read
     */
    public Boolean getRead() {
        return read;
    }
    /**
     * @param read the read to set
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

}
