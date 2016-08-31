/**
 * 
 */
package com.ymt.mirage.meilang.repository.spec;

import com.ymt.mirage.meilang.domain.Video;
import com.ymt.mirage.meilang.dto.VideoInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public class VideoSpec extends PzSimpleSpecification<Video, VideoInfo> {

	public VideoSpec(VideoInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Video> queryWraper) {
		addLikeCondition(queryWraper, "name");
		addEqualsCondition(queryWraper, "selection");
	}

}
