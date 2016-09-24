/*
 * 项目名称：mirage-lesson
 * 类名称: LessonServiceImpl
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

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.lesson.domain.Lesson;
import com.ymt.mirage.lesson.domain.LessonSet;
import com.ymt.mirage.lesson.domain.LessonTag;
import com.ymt.mirage.lesson.dto.LessonInfo;
import com.ymt.mirage.lesson.dto.SignUpState;
import com.ymt.mirage.lesson.repository.LessonRepository;
import com.ymt.mirage.lesson.repository.LessonSetRepository;
import com.ymt.mirage.lesson.repository.LessonTagRepository;
import com.ymt.mirage.lesson.repository.LessonUserRepository;
import com.ymt.mirage.lesson.repository.TeacherRepository;
import com.ymt.mirage.lesson.repository.spec.LessonSetSpec;
import com.ymt.mirage.lesson.repository.spec.LessonSpec;
import com.ymt.mirage.lesson.repository.spec.LessonTagSpec;
import com.ymt.mirage.lesson.service.LessonService;
import com.ymt.mirage.lesson.service.TeacherService;
import com.ymt.mirage.tag.dto.TagInfo;
import com.ymt.mirage.tag.service.TagService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("lessonService")
@Transactional
public class LessonServiceImpl implements LessonService {
    
    @Autowired
    private LessonRepository lessonRepository;
    
    @Autowired
    private LessonTagRepository lessonTagRepository;
    
    @Autowired
    private LessonSetRepository lessonSetRepository;
    
    @Autowired
    private LessonUserRepository lessonUserRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private TeacherService teacherService;
    
    @Override
    public Page<LessonInfo> query(LessonInfo lessonInfo, Pageable pageable) {
        if(lessonInfo.getTypeId() != null) {
            Page<LessonTag> pageData = lessonTagRepository.findAll(new LessonTagSpec(lessonInfo), pageable);
            return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<LessonTag, LessonInfo>() {
                @Override
                protected void doConvert(LessonTag domain, LessonInfo info) throws Exception {
                    BeanUtils.copyProperties(domain.getTarget(), info);
                    info.setContent("");
                    info.setTeacherName(domain.getTarget().getTeacher().getName());
                }
            });
        }else if(lessonInfo.getSetId() != null) {
            Page<LessonSet> pageData = lessonSetRepository.findAll(new LessonSetSpec(lessonInfo), pageable);
            return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<LessonSet, LessonInfo>() {
                @Override
                protected void doConvert(LessonSet domain, LessonInfo info) throws Exception {
                    BeanUtils.copyProperties(domain.getTarget(), info);
                    info.setContent("");
                    info.setTeacherName(domain.getTarget().getTeacher().getName());
                }
            });
        }else{
            Page<Lesson> pageData = lessonRepository.findAll(new LessonSpec(lessonInfo), pageable);
            return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Lesson, LessonInfo>() {
                @Override
                protected void doConvert(Lesson domain, LessonInfo info) throws Exception {
                    info.setContent("");
                    info.setTeacherName(domain.getTeacher().getName());
                }
            });
        }
    }

    @Override
    public LessonInfo create(LessonInfo lessonInfo) throws Exception {
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(lessonInfo, lesson);
        lesson.setTeacher(teacherRepository.findOne(lessonInfo.getTeacherInfo().getId()));
        lessonInfo.setId(lessonRepository.save(lesson).getId());
        tagService.addTag(lesson, lessonInfo.getTypeTags());
        tagService.addTag(lesson, lessonInfo.getSetTags(), LessonSet.class);
        return lessonInfo;
    }

    @Override
    public LessonInfo getInfo(Long id) {
        return getInfo(id, null);
    }

    @Override
    public LessonInfo getInfo(Long id, Long currentUserId) {
        Lesson lesson = lessonRepository.findOne(id);
        LessonInfo info = new LessonInfo();
        BeanUtils.copyProperties(lesson, info);
        info.setTeacherInfo(teacherService.getInfo(lesson.getTeacher().getId()));
        info.setTypeTags(tagService.getTags(lesson));
        info.setSetTags(getSetTags(lesson));
        info.setSignUpState(getSignUpState(lesson, currentUserId));
        return info;
    }

    private SignUpState getSignUpState(Lesson lesson, Long currentUserId) {
        SignUpState state;
        if(new DateTime(lesson.getSignStartTime()).isAfterNow()) {
            state = SignUpState.NOT_START; 
        }else if(new DateTime(lesson.getSignEndTime()).isBeforeNow()) {
            state = SignUpState.FINISH; 
        }else{
            state = SignUpState.NORMAL;
        }
        if(currentUserId != null) {
            if(lessonUserRepository.findByLessonIdAndUserId(lesson.getId(), currentUserId) != null) {
                state = SignUpState.SIGNUPED;
            }
        }
        return state;
    }

    private List<TagInfo> getSetTags(Lesson lesson) {
        List<TagInfo> setTags = new ArrayList<TagInfo>();
        List<LessonSet> tags = lesson.getSets();
        for (LessonSet tag : tags) {
            TagInfo tagInfo = new TagInfo();
            BeanUtils.copyProperties(tag.getTag(), tagInfo);
            setTags.add(tagInfo);
        }
        return setTags;
    }

    @Override
    public LessonInfo update(LessonInfo lessonInfo) throws Exception {
        Lesson lesson = lessonRepository.findOne(lessonInfo.getId());
        BeanUtils.copyProperties(lessonInfo, lesson);
        lesson.setTeacher(teacherRepository.findOne(lessonInfo.getTeacherInfo().getId()));
        lessonRepository.save(lesson);
        tagService.addTag(lesson, lessonInfo.getTypeTags());
        tagService.addTag(lesson, lessonInfo.getSetTags(), LessonSet.class);
        return lessonInfo;
    }

    @Override
    public void delete(Long id) {
        lessonRepository.delete(id);       
    }
    
    @Override
    public List<LessonInfo> findAll() {
        List<Object[]> lessons = lessonRepository.findAllForOption();
        List<LessonInfo> result = new ArrayList<LessonInfo>();
        for (Object[] data : lessons) {
            LessonInfo info = new LessonInfo();
            info.setId((Long) data[0]);
            info.setName((String) data[1]);
            result.add(info);
        }
        return result;
    }

}
