/*
 * 项目名称：mirage-user
 * 类名称: MessageRepository
 * 创建时间: 2016年10月24日 下午4:09:24
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ymt.mirage.user.domain.Message;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Repository
public interface MessageRepository extends PzRepository<Message> {

    Page<Message> findByUserId(Long id, Pageable pageable);

    List<Message> findByUserIdAndRead(Long userId, boolean read);
    
}
