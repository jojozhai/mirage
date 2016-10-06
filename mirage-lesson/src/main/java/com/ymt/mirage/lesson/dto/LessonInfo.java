/*
 * 项目名称：mirage-lesson
 * 类名称: LessonInfo
 * 创建时间: 2016年9月19日 上午10:42:08
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.dto;

import java.util.Date;
import java.util.List;

import com.ymt.mirage.lesson.domain.Video;
import com.ymt.mirage.tag.dto.TagInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class LessonInfo {
    
    private Long id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 一句话简介
     */
    private String desc;
    /**
     * 是否可以报名
     */
    private SignUpState signUpState;
    /**
     * 发布
     */
    private Boolean enable;

    /**
     * 发布到首页
     */
    private Boolean top;
    /**
     * 线上线下
     */
    private Boolean online;
    /**
     * 线上线下
     */
    private Boolean offline;
    /**
     * 教师
     */
    private TeacherInfo teacherInfo;
    /**
     * 
     */
    private Long teacherId;
    /**
     * 
     */
    private String teacherName;
    /**
     * 
     */
    private String teacherImage;
    /**
     * 报名开始时间
     */
    private Date signStartTime;
    /**
     * 报名结束时间
     */
    private Date signEndTime;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 开始时间
     */
    private Date startTimeTo;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 地址
     */
    private String address;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 视频地址
     */
    private String video;
    /**
     * 大图
     */
    private String image;
    /**
     * 课程介绍
     */
    private String content;
    /**
     * 分类
     */
    private List<TagInfo> typeTags;
    /**
     * 专辑
     */
    private List<TagInfo> setTags;
    /**
     * 相关商品
     */
    private List<ProductInfo> products;
    /**
     * 预告
     */
    private Boolean herald;
    /**
     * 分类id
     */
    private Long typeId;
    /**
     * 专辑id
     */
    private Long setId;
    /**
     * 推荐视频
     */
    private List<Video> videos;
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
     * @return the teacher
     */
    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacherInfo(TeacherInfo teacher) {
        this.teacherInfo = teacher;
    }

    /**
     * @return the signStartTime
     */
    public Date getSignStartTime() {
        return signStartTime;
    }

    /**
     * @param signStartTime the signStartTime to set
     */
    public void setSignStartTime(Date signStartTime) {
        this.signStartTime = signStartTime;
    }

    /**
     * @return the signEndTime
     */
    public Date getSignEndTime() {
        return signEndTime;
    }

    /**
     * @param signEndTime the signEndTime to set
     */
    public void setSignEndTime(Date signEndTime) {
        this.signEndTime = signEndTime;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the video
     */
    public String getVideo() {
        return video;
    }

    /**
     * @param video the video to set
     */
    public void setVideo(String video) {
        this.video = video;
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
     * @return the lessonTags
     */
    public List<TagInfo> getTypeTags() {
        return typeTags;
    }

    /**
     * @param lessonTags the lessonTags to set
     */
    public void setTypeTags(List<TagInfo> lessonTags) {
        this.typeTags = lessonTags;
    }

    /**
     * @return the setTags
     */
    public List<TagInfo> getSetTags() {
        return setTags;
    }

    /**
     * @param setTags the setTags to set
     */
    public void setSetTags(List<TagInfo> setTags) {
        this.setTags = setTags;
    }

    /**
     * @return the enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * @param enable the enable to set
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * @return the top
     */
    public Boolean getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(Boolean top) {
        this.top = top;
    }

    /**
     * @return the herald
     */
    public Boolean getHerald() {
        return herald;
    }

    /**
     * @param herald the herald to set
     */
    public void setHerald(Boolean herald) {
        this.herald = herald;
    }

    /**
     * @return the startTimeTo
     */
    public Date getStartTimeTo() {
        return startTimeTo;
    }

    /**
     * @param startTimeTo the startTimeTo to set
     */
    public void setStartTimeTo(Date startTimeTo) {
        this.startTimeTo = startTimeTo;
    }

    /**
     * @return the typeId
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * @return the setId
     */
    public Long getSetId() {
        return setId;
    }

    /**
     * @param setId the setId to set
     */
    public void setSetId(Long setId) {
        this.setId = setId;
    }

    /**
     * @return the signUpState
     */
    public SignUpState getSignUpState() {
        return signUpState;
    }

    /**
     * @param signUpState the signUpState to set
     */
    public void setSignUpState(SignUpState signUpState) {
        this.signUpState = signUpState;
    }

    /**
     * @return the teacherId
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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
     * @return the products
     */
    public List<ProductInfo> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<ProductInfo> products) {
        this.products = products;
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
     * @return the videos
     */
    public List<Video> getVideos() {
        return videos;
    }

    /**
     * @param videos the videos to set
     */
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    /**
     * @return the offline
     */
    public Boolean getOffline() {
        return offline;
    }

    /**
     * @param offline the offline to set
     */
    public void setOffline(Boolean offline) {
        this.offline = offline;
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
}
