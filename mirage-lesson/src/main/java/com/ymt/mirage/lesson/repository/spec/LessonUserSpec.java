/*
 * 项目名称：mirage-lesson
 * 类名称: LessonUserSpec
 * 创建时间: 2016年10月4日 上午8:19:18
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.repository.spec;

import com.ymt.mirage.lesson.domain.LessonUser;
import com.ymt.mirage.lesson.dto.LessonUserInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class LessonUserSpec extends PzSimpleSpecification<LessonUser, LessonUserInfo> {

    public LessonUserSpec(LessonUserInfo condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<LessonUser> queryWraper) {
        addEqualsCondition(queryWraper, "lessonId", "lesson.id");
        addLikeCondition(queryWraper, "nickname", "user.nickname");
    }

}
