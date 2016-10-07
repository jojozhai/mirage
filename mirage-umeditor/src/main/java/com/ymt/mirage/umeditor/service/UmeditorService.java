/*
 * 项目名称：mirage-umeditor
 * 类名称: UmeditorService
 * 创建时间: 2016年10月7日 下午10:19:17
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.umeditor.service;

import com.ymt.mirage.umeditor.dto.EditInfo;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public interface UmeditorService {

    void setProperty(EditInfo editInfo) throws Exception;

    String getProperty(EditInfo editInfo) throws Exception;

}
