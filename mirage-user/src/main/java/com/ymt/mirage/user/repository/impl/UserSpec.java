/**
 * 
 */
package com.ymt.mirage.user.repository.impl;

import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月4日
 */
public class UserSpec extends PzSimpleSpecification<User, UserInfo> {

	public UserSpec(UserInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<User> queryWraper) {
		addLikeCondition(queryWraper, "nickname");
		addLikeCondition(queryWraper, "realname");
		addLikeCondition(queryWraper, "mobile");
	}

}
