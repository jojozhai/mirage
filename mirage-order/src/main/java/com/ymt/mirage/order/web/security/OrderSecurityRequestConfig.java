/*
 * 项目名称：mirage-order
 * 类名称: OrderSecurityRequestConfig
 * 创建时间: 2016年9月20日 下午2:36:02
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.web.security;

import org.springframework.stereotype.Component;

import com.ymt.pz365.framework.core.web.security.SecurityRequestConfigAdapter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Component
public class OrderSecurityRequestConfig extends SecurityRequestConfigAdapter {
    
    @Override
    public String[] getPostRequests() {
        return new String[]{"/order"};
    }
    
    @Override
    public String[] getGetRequests() {
        return new String[]{"/order"};
    }
    
    @Override
    public String[] getPutRequests() {
        return new String[]{"/order/*"};
    }

}
