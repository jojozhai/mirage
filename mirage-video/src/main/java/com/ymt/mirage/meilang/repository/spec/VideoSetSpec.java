/**
 * 
 */
package com.ymt.mirage.meilang.repository.spec;

import com.ymt.mirage.meilang.domain.VideoSet;
import com.ymt.mirage.meilang.dto.VideoSetInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public class VideoSetSpec extends PzSimpleSpecification<VideoSet, VideoSetInfo> {

	public VideoSetSpec(VideoSetInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<VideoSet> queryWraper) {
		addLikeCondition(queryWraper, "name");
		addEqualsCondition(queryWraper, "selection");
	}

}
