/*
 * 项目名称：mirage-umeditor
 * 类名称: EditInfo
 * 创建时间: 2016年10月7日 下午10:21:31
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.umeditor.dto;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
public class EditInfo {
    
    private String target;
    
    private Long targetId;
    
    private String targetProp;
    
    private String value;
    
    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }
    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }
    /**
     * @return the targetId
     */
    public Long getTargetId() {
        return targetId;
    }
    /**
     * @param targetId the targetId to set
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
    /**
     * @return the targetProp
     */
    public String getTargetProp() {
        return targetProp;
    }
    /**
     * @param targetProp the targetProp to set
     */
    public void setTargetProp(String targetProp) {
        this.targetProp = targetProp;
    }
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
