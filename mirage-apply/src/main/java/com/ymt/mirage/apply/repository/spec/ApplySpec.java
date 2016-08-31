/**
 * 
 */
package com.ymt.mirage.apply.repository.spec;

import com.ymt.mirage.apply.domain.Apply;
import com.ymt.mirage.apply.dto.ApplyInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
public class ApplySpec extends PzSimpleSpecification<Apply, ApplyInfo> {

	public ApplySpec(ApplyInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Apply> queryWraper) {
		addEqualsCondition(queryWraper, "receiverId");
	}

}
