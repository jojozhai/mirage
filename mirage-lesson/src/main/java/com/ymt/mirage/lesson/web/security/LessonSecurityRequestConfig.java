/*
 * 项目名称：mirage-lesson
 * 类名称: LessonSecurityRequestConfig
 * 创建时间: 2016年9月19日 下午4:14:25
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.web.security;

import org.springframework.stereotype.Component;

import com.ymt.pz365.framework.core.web.security.SecurityRequestConfigAdapter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Component
public class LessonSecurityRequestConfig extends SecurityRequestConfigAdapter {
    
    @Override
    public String[] getPostRequests() {
        return new String[]{
            "/lesson/signUp" //课程报名
        };
    }

}
