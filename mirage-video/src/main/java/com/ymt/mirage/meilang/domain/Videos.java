/**
 * 
 */
package com.ymt.mirage.meilang.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ymt.pz365.data.jpa.domain.SortableImpl;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Entity
public class Videos extends SortableImpl {

	@ManyToOne
	private Video video;
	/**
	 * 
	 */
	@ManyToOne
	private VideoSet set;
	
	/**
	 * @return the video
	 */
	public Video getVideo() {
		return video;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(Video video) {
		this.video = video;
	}
	/**
	 * @return the set
	 */
	public VideoSet getSet() {
		return set;
	}
	/**
	 * @param set the set to set
	 */
	public void setSet(VideoSet set) {
		this.set = set;
	}
	
}
