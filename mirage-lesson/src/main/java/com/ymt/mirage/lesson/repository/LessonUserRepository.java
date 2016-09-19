/*
 * 项目名称：mirage-lesson
 * 类名称: TeacherRepository
 * 创建时间: 2016年9月19日 上午10:15:30
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.lesson.domain.LessonUser;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Repository
public interface LessonUserRepository extends PzRepository<LessonUser> {

    LessonUser findByLessonIdAndUserId(Long lessonId, Long userId);

}
