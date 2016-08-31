/**
 * 
 */
package com.ymt.mirage.challenge.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.challenge.domain.Teacher;
import com.ymt.mirage.challenge.dto.TeacherInfo;
import com.ymt.mirage.challenge.repository.TeacherRepository;
import com.ymt.mirage.challenge.repository.spec.TeacherSpec;
import com.ymt.mirage.challenge.service.TeacherService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public Page<TeacherInfo> query(TeacherInfo teacherInfo, Pageable pageable) {
		Page<Teacher> pageData = teacherRepository.findAll(new TeacherSpec(teacherInfo), pageable);
		return QueryResultConverter.convert(pageData, TeacherInfo.class, pageable);
	}

	@Override
	public TeacherInfo create(TeacherInfo teacherInfo) {
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherInfo, teacher);
		teacherInfo.setId(teacherRepository.save(teacher).getId());
		return teacherInfo;
	}

	@Override
	public TeacherInfo getInfo(Long id) {
		Teacher teacher = teacherRepository.findOne(id);
		TeacherInfo info = new TeacherInfo();
		BeanUtils.copyProperties(teacher, info);
		return info;
	}

	@Override
	public TeacherInfo update(TeacherInfo teacherInfo) {
		Teacher teacher = teacherRepository.findOne(teacherInfo.getId());
		BeanUtils.copyProperties(teacherInfo, teacher);
		teacherRepository.save(teacher);
		return teacherInfo;
	}

	@Override
	public void delete(Long id) {
		teacherRepository.delete(id);		
	}

	@Override
	public List<TeacherInfo> findAll() {
		List<Teacher> teachers = teacherRepository.findAll();
		return QueryResultConverter.convert(teachers, TeacherInfo.class);
	}

}
