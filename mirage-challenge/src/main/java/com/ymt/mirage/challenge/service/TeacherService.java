/**
 * 
 */
package com.ymt.mirage.challenge.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.challenge.dto.TeacherInfo;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
public interface TeacherService {

	Page<TeacherInfo> query(TeacherInfo teacherInfo, Pageable pageable);
	
	TeacherInfo create(TeacherInfo teacherInfo);

	TeacherInfo getInfo(Long id);

	TeacherInfo update(TeacherInfo teacherInfo);

	void delete(Long id);

	List<TeacherInfo> findAll();

}
