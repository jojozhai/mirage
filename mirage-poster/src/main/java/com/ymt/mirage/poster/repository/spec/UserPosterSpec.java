/**
 * 
 */
package com.ymt.mirage.poster.repository.spec;

import com.ymt.mirage.poster.domain.UserPoster;
import com.ymt.mirage.poster.dto.UserPosterInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月7日
 */
public class UserPosterSpec extends PzSimpleSpecification<UserPoster, UserPosterInfo> {

	public UserPosterSpec(UserPosterInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<UserPoster> queryWraper) {
		addEqualsCondition(queryWraper, "posterId", "poster.id");
		addLikeCondition(queryWraper, "nickname", "user.nickname");
	}

}
