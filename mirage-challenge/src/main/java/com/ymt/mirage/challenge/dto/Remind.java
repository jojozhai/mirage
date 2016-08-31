/**
 * 
 */
package com.ymt.mirage.challenge.dto;

import javax.persistence.Embeddable;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
@Embeddable
public class Remind {

	/**
	 * 是否提醒 
	 */
	private boolean remind = true;
	/**
	 * 提醒时间
	 */
	private String remindHour = "9";
	/**
	 * 提醒时间
	 */
	private String remindMinute = "00";
	/**
	 * 周一是否提醒
	 */
	private boolean remindMon = true;
	/**
	 * 周二是否提醒
	 */
	private boolean remindTue = true;
	/**
	 * 周三是否提醒
	 */
	private boolean remindWed = true;
	/**
	 * 周四是否提醒
	 */
	private boolean remindThu = true;
	/**
	 * 周五是否提醒
	 */
	private boolean remindFri = true;
	/**
	 * 周六是否提醒
	 */
	private boolean remindSat = true;
	/**
	 * 周日是否提醒
	 */
	private boolean remindSun = true;
	/**
	 * @return the remind
	 */
	public boolean isRemind() {
		return remind;
	}
	/**
	 * @param remind the remind to set
	 */
	public void setRemind(boolean remind) {
		this.remind = remind;
	}
	/**
	 * @return the remindHour
	 */
	public String getRemindHour() {
		return remindHour;
	}
	/**
	 * @param remindHour the remindHour to set
	 */
	public void setRemindHour(String remindHour) {
		this.remindHour = remindHour;
	}
	/**
	 * @return the remindMinute
	 */
	public String getRemindMinute() {
		return remindMinute;
	}
	/**
	 * @param remindMinute the remindMinute to set
	 */
	public void setRemindMinute(String remindMinute) {
		this.remindMinute = remindMinute;
	}
	/**
	 * @return the remindMon
	 */
	public boolean isRemindMon() {
		return remindMon;
	}
	/**
	 * @param remindMon the remindMon to set
	 */
	public void setRemindMon(boolean remindMon) {
		this.remindMon = remindMon;
	}
	/**
	 * @return the remindTue
	 */
	public boolean isRemindTue() {
		return remindTue;
	}
	/**
	 * @param remindTue the remindTue to set
	 */
	public void setRemindTue(boolean remindTue) {
		this.remindTue = remindTue;
	}
	/**
	 * @return the remindWed
	 */
	public boolean isRemindWed() {
		return remindWed;
	}
	/**
	 * @param remindWed the remindWed to set
	 */
	public void setRemindWed(boolean remindWed) {
		this.remindWed = remindWed;
	}
	/**
	 * @return the remindThu
	 */
	public boolean isRemindThu() {
		return remindThu;
	}
	/**
	 * @param remindThu the remindThu to set
	 */
	public void setRemindThu(boolean remindThu) {
		this.remindThu = remindThu;
	}
	/**
	 * @return the remindFri
	 */
	public boolean isRemindFri() {
		return remindFri;
	}
	/**
	 * @param remindFri the remindFri to set
	 */
	public void setRemindFri(boolean remindFri) {
		this.remindFri = remindFri;
	}
	/**
	 * @return the remindSat
	 */
	public boolean isRemindSat() {
		return remindSat;
	}
	/**
	 * @param remindSat the remindSat to set
	 */
	public void setRemindSat(boolean remindSat) {
		this.remindSat = remindSat;
	}
	/**
	 * @return the remindSun
	 */
	public boolean isRemindSun() {
		return remindSun;
	}
	/**
	 * @param remindSun the remindSun to set
	 */
	public void setRemindSun(boolean remindSun) {
		this.remindSun = remindSun;
	}
	
}
