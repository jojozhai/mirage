/*
 * 项目名称：mirage-umeditor
 * 类名称: UmeditorServiceImpl
 * 创建时间: 2016年10月7日 下午10:19:42
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.umeditor.service.impl;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ymt.mirage.umeditor.dto.EditInfo;
import com.ymt.mirage.umeditor.service.UmeditorService;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("umeditorService")
@Transactional
public class UmeditorServiceImpl implements UmeditorService {
    
    @Autowired
    private ApplicationContext applicationContext;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void setProperty(EditInfo editInfo) throws Exception {
        PzRepository repository = (PzRepository) applicationContext.getBean(editInfo.getTarget()+"Repository");
        Object domain = repository.findOne(editInfo.getTargetId());    
        BeanUtils.setProperty(domain, editInfo.getTargetProp(), editInfo.getValue());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public String getProperty(EditInfo editInfo) throws Exception {
        PzRepository repository = (PzRepository) applicationContext.getBean(editInfo.getTarget()+"Repository");
        Object domain = repository.findOne(editInfo.getTargetId());
        return BeanUtils.getProperty(domain, editInfo.getTargetProp());
    }

}
