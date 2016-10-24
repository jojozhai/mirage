/*
 * 项目名称：mirage-user
 * 类名称: MessageServiceImpl
 * 创建时间: 2016年10月24日 下午4:08:50
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.user.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.user.domain.Message;
import com.ymt.mirage.user.dto.MessageInfo;
import com.ymt.mirage.user.repository.MessageRepository;
import com.ymt.mirage.user.service.MessageService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    private MessageRepository messageRepository;

    /* (non-Javadoc)
     * @see com.ymt.mirage.user.service.MessageService#query(java.lang.Long, org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<MessageInfo> query(Long id, Pageable pageable) {
        Page<Message> messages = messageRepository.findByUserId(id, pageable);
        return QueryResultConverter.convert(messages, pageable, new AbstractDomain2InfoConverter<Message, MessageInfo>() {
            @Override
            protected void doConvert(Message domain, MessageInfo info) throws Exception {
                info.setContent("");
            }
        });
    }

    @Override
    public MessageInfo getInfo(Long id) {
        Message message = messageRepository.findOne(id);
        MessageInfo info = new MessageInfo();
        BeanUtils.copyProperties(message, info);
        return info;
    }

}
