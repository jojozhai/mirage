/*
 * 项目名称：mirage-lesson
 * 类名称: LessonSpec
 * 创建时间: 2016年9月19日 上午10:41:47
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.repository.spec;

import java.util.Date;

import com.ymt.mirage.lesson.domain.Lesson;
import com.ymt.mirage.lesson.dto.LessonInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class LessonSpec extends PzSimpleSpecification<Lesson, LessonInfo> {

    public LessonSpec(LessonInfo condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<Lesson> queryWraper) {
        addLikeCondition(queryWraper, "name");
        addEqualsCondition(queryWraper, "enable");
        addEqualsCondition(queryWraper, "top");
        addEqualsCondition(queryWraper, "herald");
        addEqualsCondition(queryWraper, "teacherId", "teacher.id");
        
        if(getCondition().getHerald() != null) {
            Date now = new Date();
            if(getCondition().getHerald()){
                addLessThanOrEqualConditionToColumn(queryWraper, "signStartTime", now);
                addGreaterThanOrEqualConditionToColumn(queryWraper, "signEndTime", now);
                addBetweenCondition(queryWraper, "startTime");
//            }else{
//                addLessThanOrEqualConditionToColumn(queryWraper, "endTime", now);
//                queryWraper.getPredicates().add(queryWraper.getCb().isNotNull(queryWraper.getRoot().get("video")));
//                queryWraper.getPredicates().add(queryWraper.getCb().notEqual(queryWraper.getRoot().get("video"), ""));
            }
        }
    }

}
