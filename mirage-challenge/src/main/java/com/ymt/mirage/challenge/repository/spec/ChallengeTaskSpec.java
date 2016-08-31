/**
 * 
 */
package com.ymt.mirage.challenge.repository.spec;

import com.ymt.mirage.challenge.domain.ChallengeTask;
import com.ymt.mirage.challenge.dto.ChallengeTaskInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public class ChallengeTaskSpec extends PzSimpleSpecification<ChallengeTask, ChallengeTaskInfo> {

	public ChallengeTaskSpec(ChallengeTaskInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<ChallengeTask> queryWraper) {
		addLikeCondition(queryWraper, "name");
		addEqualsCondition(queryWraper, "challengeId", "challenge.id");
	}

}
