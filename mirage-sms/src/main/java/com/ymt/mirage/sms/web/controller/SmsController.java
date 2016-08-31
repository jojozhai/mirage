/**
 * 
 */
package com.ymt.mirage.sms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.sms.service.SmsService;

/**
 * @author zhailiang
 * @since 2016年6月8日
 */
@RestController
@Profile("weixin")
public class SmsController {
	
	@Autowired
	private SmsService smsService;
	
	@RequestMapping(value = "/sms/code", method = RequestMethod.GET)
	public void sendSmsCode(@RequestParam String phone) {
		smsService.sendSmsCode(phone);
	}
	
	@RequestMapping(value = "/sms/code/check", method = RequestMethod.GET)
	public void checkSmsCode(@RequestParam String phone, @RequestParam String code) {
		smsService.checkSmsCode(phone, code);
	}

}
