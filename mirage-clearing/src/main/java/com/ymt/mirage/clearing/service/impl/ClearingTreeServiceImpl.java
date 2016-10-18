/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingTreeServiceImpl
 * 创建时间: 2016年9月5日 下午2:05:32
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.clearing.domain.ClearingTree;
import com.ymt.mirage.clearing.dto.ClearingTreeInfo;
import com.ymt.mirage.clearing.repository.ClearingTreeRepository;
import com.ymt.mirage.clearing.repository.spec.ClearingTreeSpec;
import com.ymt.mirage.clearing.service.ClearingTreeService;
import com.ymt.mirage.user.dto.UserInfo;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Service("clearingTreeService")
@Transactional
public class ClearingTreeServiceImpl implements ClearingTreeService {
    
    @Autowired
    private ClearingTreeRepository clearingTreeRepository;
    
    @Override
    public Page<ClearingTreeInfo> query(ClearingTreeInfo clearingTreeInfo, Pageable pageable) {
        Page<ClearingTree> pageData = clearingTreeRepository.findAll(new ClearingTreeSpec(clearingTreeInfo), pageable);
        return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<ClearingTree, ClearingTreeInfo>() {
            @Override
            protected void doConvert(ClearingTree domain, ClearingTreeInfo info) throws Exception {
                UserInfo parentInfo = new UserInfo();
                BeanUtils.copyProperties(domain.getParent().getUser(), parentInfo);
                
                UserInfo userInfo = new UserInfo();
                BeanUtils.copyProperties(domain.getUser(), userInfo);
                
                info.setParentInfo(parentInfo);
                info.setUserInfo(userInfo);
            }
        });
    }

}
