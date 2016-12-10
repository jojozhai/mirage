/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingSecurityRequestConfig
 * 创建时间: 2016年9月24日 下午3:24:17
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.web.config;

import org.springframework.stereotype.Component;

import com.ymt.pz365.framework.core.web.security.SecurityRequestConfigAdapter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Component
public class ClearingSecurityRequestConfig extends SecurityRequestConfigAdapter {

    @Override
    public String[] getPostRequests() {
        return new String[]{"/clearing", "/clearing/user", "/clearing/user2", "/withdrawale"};
    }
}
