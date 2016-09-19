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

import com.ymt.mirage.lesson.domain.LessonTag;
import com.ymt.mirage.lesson.dto.LessonInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class LessonTagSpec extends PzSimpleSpecification<LessonTag, LessonInfo> {

    public LessonTagSpec(LessonInfo condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<LessonTag> queryWraper) {
        addLikeCondition(queryWraper, "name", "target.name");
        addEqualsCondition(queryWraper, "enable", "target.enable");
        addEqualsCondition(queryWraper, "top", "target.top");
        addEqualsCondition(queryWraper, "typeId", "tag.id");
        
        if(getCondition().getHerald() != null) {
            Date now = new Date();
            if(getCondition().getHerald()){
                addLessThanOrEqualConditionToColumn(queryWraper, "target.signStartTime", now);
                addGreaterThanOrEqualConditionToColumn(queryWraper, "target.signEndTime", now);
                addBetweenCondition(queryWraper, "target.startTime");
            }else{
                addLessThanOrEqualConditionToColumn(queryWraper, "target.endTime", now);
                queryWraper.getPredicates().add(queryWraper.getCb().isNotNull(getPath(queryWraper.getRoot(),"target.video")));
                queryWraper.getPredicates().add(queryWraper.getCb().notEqual(getPath(queryWraper.getRoot(),"target.video"), ""));
            }
        }
    }

}
