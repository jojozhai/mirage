/**
 * 
 */
package com.ymt.mirage.challenge.dto;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public class ChallengeInfo {

	/**
	 * 
	 */
	private Long id;
	/**
	 * 导师
	 */
	private Long teacherId;
	/**
	 * 导师名称
	 */
	private String teacherName;
	/**
	 * 导师介绍
	 */
	private String teacherDesc;
	/**
	 * 导师名称
	 */
	private String teacherImage;
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
	private Integer days;
	/**
	 * 每节课的时长
	 */
	private String classTime;
	/**
	 * 参与者总数
	 */
	private Integer participatorCount;
	/**
	 * 任务总数
	 */
	private Integer taskCount;
	/**
	 * 图片
	 */
	private String image;
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
	public Integer getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(Integer days) {
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
	 * @return the participatorCount
	 */
	public Integer getParticipatorCount() {
		return participatorCount;
	}
	/**
	 * @param participatorCount the participatorCount to set
	 */
	public void setParticipatorCount(Integer participatorCount) {
		this.participatorCount = participatorCount;
	}
	/**
	 * @return the taskCount
	 */
	public Integer getTaskCount() {
		return taskCount;
	}
	/**
	 * @param taskCount the taskCount to set
	 */
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
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
	 * @return the teacherDesc
	 */
	public String getTeacherDesc() {
		return teacherDesc;
	}
	/**
	 * @param teacherDesc the teacherDesc to set
	 */
	public void setTeacherDesc(String teacherDesc) {
		this.teacherDesc = teacherDesc;
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
