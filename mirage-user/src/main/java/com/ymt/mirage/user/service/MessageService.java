/*
 * 项目名称：mirage-user
 * 类名称: MessageService
 * 创建时间: 2016年10月24日 下午4:07:44
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.user.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.user.dto.MessageInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface MessageService {
    
    Page<MessageInfo> query(Long id, Pageable pageable);

    MessageInfo getInfo(Long id);

}
