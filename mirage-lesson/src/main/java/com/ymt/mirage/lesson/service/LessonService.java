/**
 * 
 */
package com.ymt.mirage.lesson.service;

import java.io.File;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.lesson.dto.LessonInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface LessonService {

	Page<LessonInfo> query(LessonInfo teacherInfo, Pageable pageable);
	
	LessonInfo create(LessonInfo teacherInfo) throws Exception;

	LessonInfo getInfo(Long id);
	
	LessonInfo getInfo(Long id, Long currentUserId);

	LessonInfo update(LessonInfo teacherInfo) throws Exception;
	
	LessonInfo updateContent(LessonInfo lessonInfo);

	void delete(Long id);
	
	List<LessonInfo> findAll();

    String export(Long id, File file);

    

    
	
}
