/**
 * 
 */
package com.ymt.mirage.user.repository.impl;

import com.ymt.mirage.user.domain.Level;
import com.ymt.mirage.user.dto.LevelInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月4日
 */
public class LevelSpec extends PzSimpleSpecification<Level, LevelInfo> {

	public LevelSpec(LevelInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Level> queryWraper) {
		addLikeCondition(queryWraper, "name");
	}

}
