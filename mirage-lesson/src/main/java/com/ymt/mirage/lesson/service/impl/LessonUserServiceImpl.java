/*
 * 项目名称：mirage-lesson
 * 类名称: LessonUserServiceImpl
 * 创建时间: 2016年9月19日 下午3:55:51
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.lesson.domain.Lesson;
import com.ymt.mirage.lesson.domain.LessonUser;
import com.ymt.mirage.lesson.dto.LessonUserInfo;
import com.ymt.mirage.lesson.repository.LessonRepository;
import com.ymt.mirage.lesson.repository.LessonUserRepository;
import com.ymt.mirage.lesson.service.LessonUserService;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("lessonUserService")
@Transactional
public class LessonUserServiceImpl implements LessonUserService {
    
    @Autowired
    private LessonUserRepository lessonUserRepository;
    
    @Autowired
    private LessonRepository lessonRepository;
    
    @Autowired
    private UserRepository userRepository;

    /* (non-Javadoc)
     * @see com.ymt.mirage.lesson.service.LessonUserService#create(com.ymt.mirage.lesson.dto.LessonUserInfo)
     */
    @Override
    public void create(LessonUserInfo info) throws Exception {
        
        Lesson lesson = lessonRepository.findOne(info.getLessonId());
        lesson.signUpCheck();
        
        LessonUser lessonUser = lessonUserRepository.findByLessonIdAndUserId(info.getLessonId(), info.getUserId());
        if(lessonUser != null) {
            throw new PzException("您已经报过名了");
        }
        
        lessonUser = new LessonUser();
        lessonUser.setLesson(lesson);
        lessonUser.setUser(userRepository.getOne(info.getUserId()));
        if(info.getOnline() != null) {
            lessonUser.setOnline(info.getOnline());
        }
        lessonUserRepository.save(lessonUser);
        
    }

}
