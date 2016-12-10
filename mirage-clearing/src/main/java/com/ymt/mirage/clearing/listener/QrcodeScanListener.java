/*
 * 项目名称：mirage-clearing
 * 类名称: QrcodeScanListener
 * 创建时间: 2016年12月10日 上午11:46:25
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ymt.mirage.clearing.service.ClearingService;
import com.ymt.pz365.framework.core.context.ScanQrcodeEvent;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Component
public class QrcodeScanListener implements ApplicationListener<ScanQrcodeEvent> {

    @Autowired
    private ClearingService clearingService;
    
    @Override
    public void onApplicationEvent(ScanQrcodeEvent event) {
        clearingService.addUser2(event.getScanerId(), event.getSenderId());
    }

}
