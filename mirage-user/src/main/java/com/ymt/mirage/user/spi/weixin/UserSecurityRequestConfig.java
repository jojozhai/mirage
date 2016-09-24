/*
 * 项目名称：mirage-user
 * 类名称: UserSecurityRequestConfig
 * 创建时间: 2016年9月24日 下午2:29:12
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.user.spi.weixin;

import org.springframework.stereotype.Component;

import com.ymt.pz365.framework.core.web.security.SecurityRequestConfigAdapter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Component
public class UserSecurityRequestConfig extends SecurityRequestConfigAdapter {

    @Override
    public String[] getGetRequests() {
        return new String[]{"/user/current"};
    }
    
}
