/**
 * 
 */
package com.ymt.mirage.challenge.repository.spec;

import com.ymt.mirage.challenge.domain.Challenge;
import com.ymt.mirage.challenge.dto.ChallengeInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public class ChallengeSpec extends PzSimpleSpecification<Challenge, ChallengeInfo> {

	public ChallengeSpec(ChallengeInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Challenge> queryWraper) {
		addLikeCondition(queryWraper, "name");
	}

}
