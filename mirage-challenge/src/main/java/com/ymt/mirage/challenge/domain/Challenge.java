/**
 * 
 */
package com.ymt.mirage.challenge.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Entity
public class Challenge extends DomainImpl {

	/**
	 * 导师
	 */
	@ManyToOne
	private Teacher teacher;
	/**
	 * 导师及计划介绍的url
	 */
	private String introduceUrl;
	/**
	 * 挑战名称
	 */
	private String name;
	/**
	 * 挑战描述
	 */
	private String desc;
	/**
	 * 标签
	 */
	private String tag;
	/**
	 * 一句话介绍
	 */
	private String tip;
	/**
	 * 挑战持续天数
	 */
	private int days;
	/**
	 * 每节课的时长
	 */
	private String classTime;
	/**
	 * 参与者总数
	 */
	private int participatorCount;
	/**
	 * 任务总数
	 */
	private int taskCount;
	/**
	 * 图片
	 */
	private String image;
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
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return the classTime
	 */
	public String getClassTime() {
		return classTime;
	}
	/**
	 * @param classTime the classTime to set
	 */
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	/**
	 * @return the participatorCount
	 */
	public int getParticipatorCount() {
		return participatorCount;
	}
	/**
	 * @param participatorCount the participatorCount to set
	 */
	public void setParticipatorCount(int participatorCount) {
		this.participatorCount = participatorCount;
	}
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * @return the tip
	 */
	public String getTip() {
		return tip;
	}
	/**
	 * @param tip the tip to set
	 */
	public void setTip(String tip) {
		this.tip = tip;
	}
	/**
	 * @return the taskCount
	 */
	public int getTaskCount() {
		return taskCount;
	}
	/**
	 * @param taskCount the taskCount to set
	 */
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	/**
	 * @return the introduceUrl
	 */
	public String getIntroduceUrl() {
		return introduceUrl;
	}
	/**
	 * @param introduceUrl the introduceUrl to set
	 */
	public void setIntroduceUrl(String introduceUrl) {
		this.introduceUrl = introduceUrl;
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
	
}
