/**
 * 
 */
package com.ymt.mirage.lesson.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.lesson.dto.LessonUserInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface LessonUserService {

	void create(LessonUserInfo info) throws Exception;

    Page<LessonUserInfo> query(LessonUserInfo lessonUserInfo, Pageable pageable);

}
