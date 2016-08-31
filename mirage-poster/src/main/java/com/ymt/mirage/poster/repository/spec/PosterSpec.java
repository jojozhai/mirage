/**
 * 
 */
package com.ymt.mirage.poster.repository.spec;

import com.ymt.mirage.poster.domain.Poster;
import com.ymt.mirage.poster.dto.PosterInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public class PosterSpec extends PzSimpleSpecification<Poster, PosterInfo> {

	public PosterSpec(PosterInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Poster> queryWraper) {
		addLikeCondition(queryWraper, "name");
	}

}
