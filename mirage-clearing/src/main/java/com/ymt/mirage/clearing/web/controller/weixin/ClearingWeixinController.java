/*
 * 项目名称：mirage-clearing
 * 类名称: ClearingWeixinController
 * 创建时间: 2016年9月24日 上午10:58:31
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.clearing.web.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.clearing.dto.WithdrawalsInfo;
import com.ymt.mirage.clearing.service.ClearingService;
import com.ymt.mirage.clearing.service.WithdrawalsService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@RestController
@Profile({"weixin", "app"})
public class ClearingWeixinController {
    
    @Autowired
    private ClearingService clearingService;
    
    @Autowired
    private WithdrawalsService withdrawalsService;

    @RequestMapping(value = "/clearing/user", method = RequestMethod.POST)
    public void addUser(@RequestParam Long goodsId, @RequestParam Long sharerId) {
        clearingService.addUser(goodsId.toString(), CurrentUserHolder.getCurrentUserId(), sharerId);
    }
    
    @RequestMapping(value = "/withdrawals", method = RequestMethod.POST)
    public void getInfo(@RequestBody WithdrawalsInfo withdrawalsInfo) {
        withdrawalsInfo.setUserId(CurrentUserHolder.getCurrentUserId());
        withdrawalsService.create(withdrawalsInfo);
    }

}
