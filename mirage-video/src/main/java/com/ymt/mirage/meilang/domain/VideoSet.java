/**
 * 
 */
package com.ymt.mirage.meilang.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Entity
public class VideoSet extends Goods {
	
	@OneToMany(mappedBy = "set", cascade = CascadeType.REMOVE)
	@OrderBy("index ASC")
	private List<Videos> videos = new ArrayList<Videos>();

	/**
	 * @return the videos
	 */
	public List<Videos> getVideos() {
		return videos;
	}

	/**
	 * @param videos the videos to set
	 */
	public void setVideos(List<Videos> videos) {
		this.videos = videos;
	}

}
