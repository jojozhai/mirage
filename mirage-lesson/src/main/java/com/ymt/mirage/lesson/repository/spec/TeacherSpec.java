/*
 * 项目名称：mirage-lesson
 * 类名称: TeacherSpec
 * 创建时间: 2016年9月19日 上午10:19:28
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.repository.spec;

import com.ymt.mirage.lesson.domain.Teacher;
import com.ymt.mirage.lesson.dto.TeacherInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
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
