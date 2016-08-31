/**
 * 
 */
package com.ymt.mirage.lottery.repository.spec;

import com.ymt.mirage.lottery.domain.LotteryUser;
import com.ymt.mirage.lottery.dto.LotteryUserInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月27日
 */
public class LotteryUserSpec extends PzSimpleSpecification<LotteryUser, LotteryUserInfo> {

	public LotteryUserSpec(LotteryUserInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<LotteryUser> queryWraper) {
		addEqualsCondition(queryWraper, "lotteryId", "lottery.id");
		addLikeCondition(queryWraper, "userNickname", "user.nickname");
		addLikeCondition(queryWraper, "userRealname", "user.realname");
		addLikeCondition(queryWraper, "userMobile", "user.mobile");
	}

}
