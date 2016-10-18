/*
 * 项目名称：mirage-social
 * 类名称: SocialSecurityRequestConfig
 * 创建时间: 2016年10月10日 下午4:53:07
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.social.web.config;

import org.springframework.stereotype.Component;

import com.ymt.pz365.framework.core.web.security.SecurityRequestConfigAdapter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Component
public class SocialSecurityRequestConfig extends SecurityRequestConfigAdapter {
    
    @Override
    public String[] getPostRequests() {
        return new String[]{"/praise", "/comment"};
    }
    
    @Override
    public String[] getGetRequests() {
        return new String[]{"/praise"};
    }
    
    @Override
    public String[] getPutRequests() {
        return new String[]{"/praise"};
    }

}
