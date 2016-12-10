/*
 * 项目名称：mirage-lesson
 * 类名称: Lesson
 * 创建时间: 2016年9月2日 上午9:00:11
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.ymt.mirage.tag.domain.Tagable;
import com.ymt.pz365.data.jpa.domain.DomainImpl;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Entity
public class Lesson extends DomainImpl implements Tagable {
    
    /**
     * 课程名称
     */
    private String name;
    /**
     * 一句话简介
     */
    private String desc;
    /**
     * 分享标题
     */
    private String shareTitle;
    /**
     * 描述
     */
    private String shareTip;
    /**
     * 分享报名
     */
    private boolean shareSign;
    /**
     * 分享报名提示
     */
    private String shareSignTip;
    /**
     * 发布
     */
    private boolean enable;
    /**
     * 是否是预告
     */
    private boolean herald;
    /**
     * 在线
     */
    private boolean online;
    /**
     * 在线
     */
    private boolean offline;
    /**
     * 置顶
     */
    private boolean top;
    /**
     * 置顶顺序
     */
    private int topIndex;
    /**
     * 报名人数限制
     */
    private int signLimit;
    /**
     * 教师
     */
    @ManyToOne
    private Teacher teacher;
    /**
     * 报名开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date signStartTime;
    /**
     * 报名结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date signEndTime;
    /**
     * 开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    /**
     * 结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
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
    @Lob
    private String content;
    /**
     * 推荐视频
     */
    @ElementCollection
    private List<Video> videos;
    /**
     * 分类
     */
    @OneToMany(mappedBy = "target", cascade = CascadeType.REMOVE)
    private List<LessonTag> tags = new ArrayList<LessonTag>();
    /**
     * 专辑
     */
    @OneToMany(mappedBy = "target", cascade = CascadeType.REMOVE)
    private List<LessonSet> sets = new ArrayList<LessonSet>();
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
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }
    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
     * @return the tags
     */
    public List<LessonTag> getTags() {
        return tags;
    }
    /**
     * @param tags the tags to set
     */
    public void setTags(List<LessonTag> tags) {
        this.tags = tags;
    }
    /**
     * @return the sets
     */
    public List<LessonSet> getSets() {
        return sets;
    }
    /**
     * @param sets the sets to set
     */
    public void setSets(List<LessonSet> sets) {
        this.sets = sets;
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
     * @return the enable
     */
    public boolean isEnable() {
        return enable;
    }
    /**
     * @param enable the enable to set
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    /**
     * @return the top
     */
    public boolean isTop() {
        return top;
    }
    /**
     * @param top the top to set
     */
    public void setTop(boolean top) {
        this.top = top;
    }
    public void signUpCheck() {
        if(!isSignUpAble()) {
            throw new PzException("报名失败, 报名已结束");
        }
        
    }
    public boolean isSignUpAble() {
        return new DateTime(getSignStartTime()).isBeforeNow() &&
                new DateTime(getSignEndTime()).isAfterNow() && 
                isEnable();
    }
    /**
     * @return the herald
     */
    public boolean isHerald() {
        return herald;
    }
    /**
     * @param herald the herald to set
     */
    public void setHerald(boolean herald) {
        this.herald = herald;
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
    public boolean isOffline() {
        return offline;
    }
    /**
     * @param offline the offline to set
     */
    public void setOffline(boolean offline) {
        this.offline = offline;
    }
    /**
     * @return the shareTitle
     */
    public String getShareTitle() {
        return shareTitle;
    }
    /**
     * @param shareTitle the shareTitle to set
     */
    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }
    /**
     * @return the shareTip
     */
    public String getShareTip() {
        return shareTip;
    }
    /**
     * @param shareTip the shareTip to set
     */
    public void setShareTip(String shareTip) {
        this.shareTip = shareTip;
    }
    /**
     * @return the shareSign
     */
    public boolean isShareSign() {
        return shareSign;
    }
    /**
     * @param shareSign the shareSign to set
     */
    public void setShareSign(boolean shareSign) {
        this.shareSign = shareSign;
    }
    /**
     * @return the shareSignTip
     */
    public String getShareSignTip() {
        return shareSignTip;
    }
    /**
     * @param shareSignTip the shareSignTip to set
     */
    public void setShareSignTip(String shareSignTip) {
        this.shareSignTip = shareSignTip;
    }
    /**
     * @return the topIndex
     */
    public int getTopIndex() {
        return topIndex;
    }
    /**
     * @param topIndex the topIndex to set
     */
    public void setTopIndex(int topIndex) {
        this.topIndex = topIndex;
    }
    /**
     * @return the signLimit
     */
    public int getSignLimit() {
        return signLimit;
    }
    /**
     * @param signLimit the signLimit to set
     */
    public void setSignLimit(int signLimit) {
        this.signLimit = signLimit;
    }
    
}
