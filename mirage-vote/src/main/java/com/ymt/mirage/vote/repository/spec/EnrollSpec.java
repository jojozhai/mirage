/**
 * 
 */
package com.ymt.mirage.vote.repository.spec;

import com.ymt.mirage.vote.domain.Enroll;
import com.ymt.mirage.vote.dto.EnrollInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
public class EnrollSpec extends PzSimpleSpecification<Enroll, EnrollInfo> {

	public EnrollSpec(EnrollInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Enroll> queryWraper) {
		addEqualsCondition(queryWraper, "activityId", "activity.id");
		addLikeCondition(queryWraper, "name");
		addEqualsCondition(queryWraper, "number");
	}

}
