/**
 * 
 */
package com.ymt.mirage.challenge.repository.spec;

import com.ymt.mirage.challenge.domain.Teacher;
import com.ymt.mirage.challenge.dto.TeacherInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
public class TeacherSpec extends PzSimpleSpecification<Teacher, TeacherInfo> {

	public TeacherSpec(TeacherInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Teacher> queryWraper) {
		addLikeCondition(queryWraper, "name");
	}

}
