/**
 * 
 */
package com.ymt.mirage.meilang.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Entity
public class Video extends Goods {
	
	/**
	 * 时长
	 */
	private String duration;
	/**
	 * 视频url
	 */
	@Column(length = 1000)
	private String url;
	/**
	 * 播放用的url，带有过期时间和签名
	 */
	private String playUrl;
	/**
	 * 播放链接的到期时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date playUrlExpire;
	/**
	 * 所属套餐
	 */
	@OneToMany(mappedBy = "video", cascade = CascadeType.REMOVE)
	private Set<Videos> sets = new HashSet<Videos>();
	/**
	 * 播放次数
	 */
	private int playCount;
	
	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the sets
	 */
	public Set<Videos> getSets() {
		return sets;
	}
	/**
	 * @param sets the sets to set
	 */
	public void setSets(Set<Videos> sets) {
		this.sets = sets;
	}
	/**
	 * @return the playCount
	 */
	public int getPlayCount() {
		return playCount;
	}
	/**
	 * @param playCount the playCount to set
	 */
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	/**
	 * @return the playUrl
	 */
	public String getPlayUrl() {
		return playUrl;
	}
	/**
	 * @param playUrl the playUrl to set
	 */
	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	/**
	 * @return the playUrlExpire
	 */
	public Date getPlayUrlExpire() {
		return playUrlExpire;
	}
	/**
	 * @param playUrlExpire the playUrlExpire to set
	 */
	public void setPlayUrlExpire(Date playUrlExpire) {
		this.playUrlExpire = playUrlExpire;
	}
	
}
