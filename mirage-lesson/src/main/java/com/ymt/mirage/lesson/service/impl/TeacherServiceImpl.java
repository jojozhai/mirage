/*
 * 项目名称：mirage-lesson
 * 类名称: TeacherServiceImpl
 * 创建时间: 2016年9月19日 上午10:18:05
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.lesson.domain.Teacher;
import com.ymt.mirage.lesson.dto.TeacherInfo;
import com.ymt.mirage.lesson.repository.TeacherRepository;
import com.ymt.mirage.lesson.repository.spec.TeacherSpec;
import com.ymt.mirage.lesson.service.TeacherService;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
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
        List<Object[]> teachers = teacherRepository.findAllForOption();
        List<TeacherInfo> result = new ArrayList<TeacherInfo>();
        for (Object[] data : teachers) {
            TeacherInfo info = new TeacherInfo();
            info.setId((Long) data[0]);
            info.setName((String) data[1]);
            result.add(info);
        }
        return result;
    }

}
